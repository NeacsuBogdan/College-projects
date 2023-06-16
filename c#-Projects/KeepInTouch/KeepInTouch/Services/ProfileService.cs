using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using KeepInTouch.Models;
using Microsoft.EntityFrameworkCore;

namespace KeepInTouch.Services
{
    public class ProfileService
    {
        private readonly MyDbContext _dbContext;

        public ProfileService(MyDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<ProfileModel> GetProfile(int userId)
        {
            var user = await _dbContext.Users.FirstOrDefaultAsync(u => u.Id == userId);
            if (user == null)
            {
                return null;
            }

            var profileModel = new ProfileModel
            {
                UserId = user.Id,
                Username = user.Username,
                Bio = user.Bio,
                ProfilePictureUrl = user.ProfilePictureUrl,
                Followers = await GetFollowers(userId),
                Following = await GetFollowing(userId)
            };

            return profileModel;
        }

        public async Task<bool> UpdateBio(int userId, string newBio)
        {
            var user = await _dbContext.Users.FirstOrDefaultAsync(u => u.Id == userId);
            if (user == null)
            {
                return false; // User not found
            }

            user.Bio = newBio;
            await _dbContext.SaveChangesAsync();

            return true; // Bio updated successfully
        }

        public async Task<bool> UpdateProfilePicture(int userId, string newProfilePictureUrl)
        {
            var user = await _dbContext.Users.FirstOrDefaultAsync(u => u.Id == userId);
            if (user == null)
            {
                return false; // User not found
            }

            user.ProfilePictureUrl = newProfilePictureUrl;
            await _dbContext.SaveChangesAsync();

            return true; // Profile picture updated successfully
        }

        public async Task<bool> FollowUser(int followerId, int followedId)
        {
            var followedByUser = await _dbContext.Users.FindAsync(followerId);
            var followingUser = await _dbContext.Users.FindAsync(followedId);

            if (followedByUser == null || followingUser == null)
            {
                return false; // User not found
            }

            var existingFollow = await _dbContext.Follows.FirstOrDefaultAsync(f => f.FollowerId == followerId && f.FollowingId == followedId);

            if (existingFollow != null)
            {
                return false; // User is already a follower
            }

            var follow = new Follow
            {
                FollowerId = followerId,
                FollowingId = followedId
            };

            _dbContext.Follows.Add(follow);
            await _dbContext.SaveChangesAsync();

            return true; // User added as follower
        }

        public async Task<bool> UnfollowUser(int followerId, int followedId)
        {
            var follow = await _dbContext.Follows.FirstOrDefaultAsync(f => f.FollowerId == followerId && f.FollowingId == followedId);

            if (follow == null)
            {
                return false; // User is not a follower
            }

            _dbContext.Follows.Remove(follow);
            await _dbContext.SaveChangesAsync();

            return true; // User removed as follower
        }

        private async Task<List<UserModel>> GetFollowers(int userId)
        {
            var followerIds = await _dbContext.Follows
                .Where(f => f.FollowingId == userId)
                .Select(f => f.FollowerId)
                .ToListAsync();

            var followers = await _dbContext.Users
                .Where(u => followerIds.Contains(u.Id))
                .Select(u => new UserModel
                {
                    UserId = u.Id,
                    Username = u.Username
                })
                .ToListAsync();

            return followers;
        }

        private async Task<List<UserModel>> GetFollowing(int userId)
        {
            var followedIds = await _dbContext.Follows
                .Where(f => f.FollowerId == userId)
                .Select(f => f.FollowingId)
                .ToListAsync();

            var following = await _dbContext.Users
                .Where(u => followedIds.Contains(u.Id))
                .Select(u => new UserModel
                {
                    UserId = u.Id,
                    Username = u.Username
                })
                .ToListAsync();

            return following;
        }

        public class UserModel
        {
            public int UserId { get; set; }
            public string Username { get; set; }
        }

        public class ProfileModel
        {
            public int UserId { get; set; }
            public string Username { get; set; }
            public string Bio { get; set; }
            public string ProfilePictureUrl { get; set; }
            public List<UserModel> Followers { get; set; }
            public List<UserModel> Following { get; set; }
        }
    }
}
