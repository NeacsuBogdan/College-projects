using System.ComponentModel.DataAnnotations.Schema;
using System.Xml.Linq;

namespace KeepInTouch.Models
{
    public class Post
    {
        public int Id { get; set; }

        [ForeignKey("User")]
        public int UserId { get; set; }
        public User User { get; set; }

        public string Description { get; set; }
        public string ImageUrl { get; set; }
        public DateTime Timestamp { get; set; }

        public ICollection<Like> Likes { get; set; }
        public ICollection<Comment> Comments { get; set; }
    }
}
