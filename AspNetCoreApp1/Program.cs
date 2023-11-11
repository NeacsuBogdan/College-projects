using Microsoft.EntityFrameworkCore;
using DoodleTech.Data;
using DoodleTech.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();
builder.Services.AddDbContext<ApplicationDbContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("DatabaseConnectionString")));

// Înregistrează serviciile
builder.Services.AddScoped<RegisterService>();
builder.Services.AddScoped<LoginService>();

builder.Services.AddAuthentication().AddCookie();  // Added this line
builder.Services.AddAuthorization();  // Added this line

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    app.UseHsts();
}

// Commented out for now to make it easier to test in Docker without HTTPS
// app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseRouting();

app.UseAuthentication();  // Added this line
app.UseAuthorization();  // Added this line

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
