using KeepInTouch.Models;
using System.ComponentModel.DataAnnotations;

public class User
{
    public int Id { get; set; }

    [Required(ErrorMessage = "Username is required.")]
    public string Username { get; set; }

    [Required(ErrorMessage = "Email is required.")]
    public string Email { get; set; }

    [Required(ErrorMessage = "Password is required.")]
    public string Password { get; set; }

    public string? Bio { get; set; }
    public string? ProfilePictureUrl { get; set; }

    public ICollection<Post>? Posts { get; set; }
    public ICollection<Like>? Likes { get; set; }
    public ICollection<Comment>? Comments { get; set; }
    public ICollection<Follow>? Followers { get; set; }
    public ICollection<Follow>? Following { get; set; }
}