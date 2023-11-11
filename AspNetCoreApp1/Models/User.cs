using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace DoodleTech.Models
{
    public class User
    {
        [Key]
        public int UserId { get; set; }
        [Required, MaxLength(255)]
        public string Username { get; set; }
        [Required, MaxLength(255)]
        public string Password { get; set; }
        [Required, MaxLength(255), Unique]
        public string Email { get; set; }
    }

    internal class UniqueAttribute : Attribute
    {
    }
}