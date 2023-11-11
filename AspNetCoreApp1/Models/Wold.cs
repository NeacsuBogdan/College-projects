using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DoodleTech.Models
{
    public class World
    {
        [Key]
        public int WorldId { get; set; }
        [Required]
        public int UserId { get; set; }
        [Required, MaxLength(255)]
        public string WorldName { get; set; }
        public string ActiveElements { get; set; }
        [ForeignKey(nameof(UserId))]
        public User User { get; set; }
    }
}
