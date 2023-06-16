using KeepInTouch.Models;
using KeepInTouch.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Linq;
using static KeepInTouch.Services.PostService;

namespace KeepInTouch.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProfileController : ControllerBase
    {
        private readonly MyDbContext _dbContext;
        private readonly PostService _postService;
        private readonly ProfileService _profileService;

        public ProfileController(MyDbContext dbContext, PostService postService, ProfileService profileService)
        {
            _dbContext = dbContext;
            _postService = postService;
            _profileService = profileService;
        }

        [HttpGet("{userId}")]
        public async Task<IActionResult> GetProfile(int userId)
        {
            try
            {
                var user = await _dbContext.Users.FirstOrDefaultAsync(u => u.Id == userId);
                if (user == null)
                {
                    return NotFound();
                }

                var userPosts = await _postService.GetPostsByUserId(userId);
                var profileModel = new ProfileModel
                {
                    UserId = user.Id,
                    Username = user.Username,
                    Bio = user.Bio,
                    ProfilePictureUrl = user.ProfilePictureUrl,
                    Posts = userPosts
                };

                return Ok(profileModel);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpPost("{userId}/bio")]
        public async Task<IActionResult> UpdateBio(int userId, [FromBody] string newBio)
        {
            try
            {
                var success = await _profileService.UpdateBio(userId, newBio);
                if (success)
                {
                    return Ok();
                }
                else
                {
                    return BadRequest("Failed to update bio");
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpPost("{userId}/profilepicture")]
        public async Task<IActionResult> UpdateProfilePicture(int userId, [FromBody] string newProfilePictureUrl)
        {
            try
            {
                var success = await _profileService.UpdateProfilePicture(userId, newProfilePictureUrl);
                if (success)
                {
                    return Ok();
                }
                else
                {
                    return BadRequest("Failed to update profile picture");
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpPost("{followedId}/follow/{followerId}")]
        public async Task<IActionResult> FollowUser(int followedId, int followerId)
        {
            try
            {
                var success = await _profileService.FollowUser(followerId, followedId);
                if (success)
                {
                    return Ok();
                }
                else
                {
                    return BadRequest("Failed to follow user");
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.ToString());
            }
        }

        [HttpDelete("{followedId}/unfollow/{followerId}")]
        public async Task<IActionResult> UnfollowUser(int followedId, int followerId)
        {
            try
            {
                var success = await _profileService.UnfollowUser(followerId, followedId);
                if (success)
                {
                    return Ok();
                }
                else
                {
                    return BadRequest("Failed to unfollow user");
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpPost("{userId}/posts")]
        public async Task<IActionResult> CreatePost(int userId, [FromBody] CreatePostModel postModel)
        {
            try
            {
                var postId = await _postService.CreatePost(postModel, userId);
                if (postId > 0)
                {
                    return Ok(postId);
                }
                else
                {
                    return BadRequest("Failed to create post");
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        public class ProfileModel
        {
            public int UserId { get; set; }
            public string Username { get; set; }
            public string Bio { get; set; }
            public string ProfilePictureUrl { get; set; }
            public List<PostModel> Posts { get; set; }
        }
    }
}
