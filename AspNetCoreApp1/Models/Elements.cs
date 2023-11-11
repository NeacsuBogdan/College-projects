using System.ComponentModel.DataAnnotations;

namespace DoodleTech.Models
{
    public class Elements
    {
        [Key]
        public int ElementId { get; set; }
        [Required, MaxLength(255)]
        public string ElementName { get; set; }
        public string Description { get; set; }
    }
}
