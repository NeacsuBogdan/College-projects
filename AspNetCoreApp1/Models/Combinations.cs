using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DoodleTech.Models
{
    public class Combinations
    {
        [Key]
        public int CombinationId { get; set; }
        [Required]
        public int Element1Id { get; set; }
        [Required]
        public int Element2Id { get; set; }
        [Required]
        public int ResultElementId { get; set; }
        [ForeignKey(nameof(Element1Id))]
        public Elements Element1 { get; set; }
        [ForeignKey(nameof(Element2Id))]
        public Elements Element2 { get; set; }
        [ForeignKey(nameof(ResultElementId))]
        public Elements ResultElement { get; set; }
    }
}
