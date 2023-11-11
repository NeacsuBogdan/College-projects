using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DoodleTech.Models
{
    public class UserElements
    {
        [Key]
        public int UserElementId { get; set; }
        [Required]
        public int UserId { get; set; }
        [Required]
        public int ElementId { get; set; }
        [ForeignKey(nameof(UserId))]
        public User User { get; set; }
        [ForeignKey(nameof(ElementId))]
        public Elements Element { get; set; }
    }
}
