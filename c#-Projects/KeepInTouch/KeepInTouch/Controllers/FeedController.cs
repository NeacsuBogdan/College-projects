using KeepInTouch.Models;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Security.Claims;
using System.Text.Json;

namespace KeepInTouch.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class FeedController : ControllerBase
    {
        private readonly MyDbContext _dbContext;
        private readonly IConfiguration _configuration;

        public FeedController(MyDbContext dbContext, IConfiguration configuration)
        {
            _dbContext = dbContext;
            _configuration = configuration;
        }

        [Authorize]
        [HttpGet]
        public IActionResult GetFeed()
        {
            try
            {
                var userId = GetUserIdFromToken();

                var followingIds = _dbContext.Follows
                    .Where(f => f.FollowerId == userId)
                    .Select(f => f.FollowingId)
                    .ToList();

                var currentDate = DateTime.UtcNow;
                var threeDaysAgo = currentDate.AddDays(-3);

                var feedPosts = _dbContext.Posts
                    .Include(p => p.User)
                    .Where(p => followingIds.Contains(p.UserId) && p.Timestamp >= threeDaysAgo)
                    .OrderByDescending(p => p.Timestamp)
                    .Select(p => new
                    {
                        Username = p.User.Username,
                        PostId = p.Id,
                        Description = p.Description,
                        ImageUrl = p.ImageUrl
                    })
                    .ToList();

                var json = JsonSerializer.Serialize(feedPosts);
                return Content(json, "application/json");
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }


        private int GetUserIdFromToken()
        {
            var userIdClaim = User.FindFirst(ClaimTypes.NameIdentifier);

            if (userIdClaim != null && int.TryParse(userIdClaim.Value, out int userId))
            {
                return userId;
            }

            throw new InvalidOperationException("Unable to retrieve user ID from token.");
        }
    }
}
