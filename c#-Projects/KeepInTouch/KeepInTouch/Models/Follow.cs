using System.ComponentModel.DataAnnotations.Schema;

namespace KeepInTouch.Models
{
    public class Follow
    {
        [ForeignKey("Follower")]
        public int FollowerId { get; set; }
        public User Follower { get; set; }

        [ForeignKey("Following")]
        public int FollowingId { get; set; }
        public User Following { get; set; }
    }
}
