using System.ComponentModel.DataAnnotations.Schema;

namespace KeepInTouch.Models
{
    public class Comment
    {
        public int Id { get; set; }

        [ForeignKey("User")]
        public int UserId { get; set; }
        public User User { get; set; }

        [ForeignKey("Post")]
        public int PostId { get; set; }
        public Post Post { get; set; }

        public string Text { get; set; }
        public DateTime Timestamp { get; set; }
    }
}
