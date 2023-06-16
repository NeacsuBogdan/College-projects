using KeepInTouch.Models;
using KeepInTouch.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Security.Claims;
using System.Threading.Tasks;

namespace KeepInTouch.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PostsController : ControllerBase
    {
        private readonly PostService _postService;

        public PostsController(PostService postService)
        {
            _postService = postService;
        }

        [Authorize]
        [HttpPost("create")]
        public async Task<IActionResult> CreatePost([FromBody] PostService.CreatePostModel postModel)
        {
            try
            {
                var userId = GetUserIdFromToken();

                var postId = await _postService.CreatePost(postModel, userId);

                return Ok(postId);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [Authorize]
        [HttpPost("{postId}/like")]
        public async Task<IActionResult> AddLike(int postId)
        {
            try
            {
                var userId = GetUserIdFromToken();

                var success = await _postService.AddLike(postId, userId);

                if (success)
                {
                    return Ok();
                }
                else
                {
                    return BadRequest("Like already exists");
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [HttpGet("{postId}")]
        public async Task<IActionResult> GetPost(int postId)
        {
            try
            {
                var post = await _postService.GetPost(postId);
                if (post == null)
                {
                    return NotFound();
                }

                return Ok(post);
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [Authorize]
        [HttpPost("{postId}/comment")]
        public async Task<IActionResult> AddComment(int postId, [FromBody] PostService.AddCommentModel commentModel)
        {
            try
            {
                var userId = GetUserIdFromToken();

                var success = await _postService.AddComment(postId, userId, commentModel.Text);

                if (success)
                {
                    return Ok();
                }
                else
                {
                    return BadRequest("Failed to add comment");
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [Authorize]
        [HttpDelete("{postId}")]
        public async Task<IActionResult> DeletePost(int postId)
        {
            try
            {
                var success = await _postService.DeletePost(postId);

                if (success)
                {
                    return Ok();
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }
        [Authorize]
        [HttpPut("{postId}/comment/{commentId}")]
        public async Task<IActionResult> EditComment(int postId, int commentId, [FromBody] PostService.AddCommentModel model)
        {
            try
            {
                var success = await _postService.EditComment(commentId, model.Text);

                if (success)
                {
                    return Ok();
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }


        [Authorize]
        [HttpDelete("{postId}/comment/{commentId}")]
        public async Task<IActionResult> DeleteComment(int postId, int commentId)
        {
            try
            {
                var success = await _postService.DeleteComment(commentId);

                if (success)
                {
                    return Ok();
                }
                else
                {
                    return NotFound();
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500, ex.Message);
            }
        }

        [Authorize]
        [HttpDelete("{postId}/like/{likeId}")]
        public async Task<IActionResult> DeleteLike(int postId, int likeId)
        {
            try
            {
                var success = await _postService.DeleteLike(likeId);

                if (success)
                {
                    return Ok();
                }
                else
                {
                    return NotFound();
                }
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

        public class EditCommentModel
        {
            public string NewText { get; set; }
        }
    }
}
