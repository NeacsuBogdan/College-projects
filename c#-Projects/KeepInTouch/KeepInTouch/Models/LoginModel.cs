using System.ComponentModel.DataAnnotations.Schema;

namespace KeepInTouch.Models
{
    [NotMapped]
    public class LoginModel
    {
        public string Username { get; set; }
        public string Password { get; set; }
    }
}
