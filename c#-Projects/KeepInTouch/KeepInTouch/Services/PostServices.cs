using KeepInTouch.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using static KeepInTouch.Controllers.PostsController;

namespace KeepInTouch.Services
{
    public class PostService
    {
        private readonly MyDbContext _dbContext;

        public PostService(MyDbContext dbContext)
        {
            _dbContext = dbContext;
        }


        public class CreatePostModel
        {
            public string Description { get; set; }
            public string ImageUrl { get; set; }
        }

        public class PostModel
        {
            public int PostId { get; set; }
            public string Description { get; set; }
            public string ImageUrl { get; set; }
            public DateTime Timestamp { get; set; }
            public List<LikeModel> Likes { get; set; }
            public List<CommentModel> Comments { get; set; }
        }

        public class LikeModel
        {
            public int UserId { get; set; }
            public string Username { get; set; }
        }

        public class AddCommentModel
        {
            public string Text { get; set; }
        }

        public class CommentModel
        {
            public int CommentId { get; set; }
            public int UserId { get; set; }
            public string Username { get; set; }
            public string Text { get; set; }
            public DateTime Timestamp { get; set; }
        }

        public async Task<int> CreatePost(CreatePostModel postModel, int userId)
        {
            var post = new Post
            {
                UserId = userId,
                Description = postModel.Description,
                ImageUrl = postModel.ImageUrl,
                Timestamp = DateTime.UtcNow
            };

            _dbContext.Posts.Add(post);
            await _dbContext.SaveChangesAsync();

            return post.Id;
        }

        public async Task<List<PostModel>> GetAllPosts()
        {
            var posts = await _dbContext.Posts.ToListAsync();

            var postModels = posts.Select(p => new PostModel
            {
                PostId = p.Id,
                Description = p.Description,
                ImageUrl = p.ImageUrl,
                Timestamp = p.Timestamp,
                Likes = GetPostLikes(p.Id),
                Comments = GetPostComments(p.Id)
            }).ToList();

            return postModels;
        }

        public async Task<PostModel> GetPost(int postId)
        {
            var post = await _dbContext.Posts.FirstOrDefaultAsync(p => p.Id == postId);
            if (post == null)
            {
                return null;
            }

            var postModel = new PostModel
            {
                PostId = post.Id,
                Description = post.Description,
                ImageUrl = post.ImageUrl,
                Timestamp = post.Timestamp,
                Likes = GetPostLikes(post.Id),
                Comments = GetPostComments(post.Id)
            };

            return postModel;
        }

        public async Task<List<PostModel>> GetPostsByUserId(int userId)
        {
            var posts = await _dbContext.Posts
                .Where(p => p.UserId == userId)
                .ToListAsync();

            var postModels = new List<PostModel>();

            foreach (var post in posts)
            {
                var postModel = await GetPost(post.Id);
                postModels.Add(postModel);
            }

            return postModels;
        }

        public async Task<bool> AddLike(int postId, int userId)
        {
            var existingLike = await _dbContext.Likes.FirstOrDefaultAsync(l => l.PostId == postId && l.UserId == userId);
            if (existingLike != null)
            {
                return false; // Like already exists
            }

            var like = new Like
            {
                UserId = userId,
                PostId = postId
            };

            _dbContext.Likes.Add(like);
            await _dbContext.SaveChangesAsync();

            return true; // Like added successfully
        }

        public async Task<bool> AddComment(int postId, int userId, string text)
        {
            var comment = new Comment
            {
                UserId = userId,
                PostId = postId,
                Text = text,
                Timestamp = DateTime.UtcNow
            };

            _dbContext.Comments.Add(comment);
            await _dbContext.SaveChangesAsync();

            return true; // Comment added successfully
        }

        public async Task<bool> DeletePost(int postId)
        {
            var post = await _dbContext.Posts.FirstOrDefaultAsync(p => p.Id == postId);
            if (post == null)
            {
                return false; // Post not found
            }

            _dbContext.Posts.Remove(post);
            await _dbContext.SaveChangesAsync();

            return true; // Post deleted successfully
        }

        public async Task<bool> DeleteComment(int commentId)
        {
            var comment = await _dbContext.Comments.FirstOrDefaultAsync(c => c.Id == commentId);
            if (comment == null)
            {
                return false; // Comment not found
            }

            _dbContext.Comments.Remove(comment);
            await _dbContext.SaveChangesAsync();

            return true; // Comment deleted successfully
        }

        public async Task<bool> DeleteLike(int likeId)
        {
            var like = await _dbContext.Likes.FirstOrDefaultAsync(l => l.Id == likeId);
            if (like == null)
            {
                return false; // Like not found
            }

            _dbContext.Likes.Remove(like);
            await _dbContext.SaveChangesAsync();

            return true; // Like deleted successfully
        }

        public async Task<bool> EditComment(int commentId, string newText)
        {
            var comment = await _dbContext.Comments.FirstOrDefaultAsync(c => c.Id == commentId);
            if (comment == null)
            {
                return false; // Comment not found
            }

            comment.Text = newText;
            await _dbContext.SaveChangesAsync();

            return true; // Comment edited successfully
        }

        private List<LikeModel> GetPostLikes(int postId)
        {
            var likes = _dbContext.Likes.Where(l => l.PostId == postId).ToList();
            var likeModels = likes.Select(l => new LikeModel
            {
                UserId = l.UserId,
                Username = _dbContext.Users.FirstOrDefault(u => u.Id == l.UserId)?.Username
            }).ToList();

            return likeModels;
        }

        private List<CommentModel> GetPostComments(int postId)
        {
            var comments = _dbContext.Comments.Where(c => c.PostId == postId).ToList();
            var commentModels = comments.Select(c => new CommentModel
            {
                CommentId = c.Id,
                UserId = c.UserId,
                Username = _dbContext.Users.FirstOrDefault(u => u.Id == c.UserId)?.Username,
                Text = c.Text,
                Timestamp = c.Timestamp
            }).ToList();

            return commentModels;
        }
    }
}
