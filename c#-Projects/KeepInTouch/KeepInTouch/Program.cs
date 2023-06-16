using KeepInTouch.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using System.Text;
using KeepInTouch.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages();
builder.Services.AddDbContext<MyDbContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection")));
builder.Services.AddControllers();
builder.Services.AddScoped<UserService>();
builder.Services.AddScoped<PostService>();
builder.Services.AddScoped<ProfileService>();
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuerSigningKey = true,
            ValidateLifetime = true,
            ValidateAudience = true,
            ValidAudience = "https://localhost:7006",
            ValidateIssuer = true, // This line ensures that the issuer is validated
            ValidIssuer = "KeepInTouch", // This line specifies the expected issuer
            IssuerSigningKey = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]))
        };
    });

builder.Services.AddAuthorization();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();

app.UseEndpoints(endpoints =>
{
    endpoints.MapRazorPages();
    endpoints.MapControllers();
    endpoints.MapControllerRoute(
        name: "default",
        pattern: "{controller=Home}/{action=Index}/{id?}");
    endpoints.MapControllerRoute(
        name: "test",
        pattern: "api/account/test",
        defaults: new { controller = "Account", action = "Test" });
    endpoints.MapControllerRoute(
        name: "feed",
        pattern: "feed",
        defaults: new { controller = "Feed", action = "Index" });
    endpoints.MapControllerRoute(
    name: "posts",
    pattern: "posts",
    defaults: new { controller = "Posts", action = "GetPost" });
    endpoints.MapControllerRoute(
    name: "profile",
    pattern: "api/profile/{userId}",
    defaults: new { controller = "Profile", action = "GetProfile" });
});


app.Run();
