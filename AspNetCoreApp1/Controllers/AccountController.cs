using DoodleTech.Services;  // Actualizează acest namespace pentru a se potrivi proiectului tău
using Microsoft.AspNetCore.Mvc;
using AspNetCoreApp1.Models;
using DoodleTech.Models;
using System.Security.Claims;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Authentication;  // Actualizează acest namespace pentru a se potrivi proiectului tău

namespace DoodleTech.Controllers  // Actualizează acest namespace pentru a se potrivi proiectului tău
{
    public class AccountController : Controller
    {
        private readonly RegisterService _registerService;
        private readonly LoginService _loginService;

        public AccountController(RegisterService registerService, LoginService loginService)
        {
            _registerService = registerService;
            _loginService = loginService;
        }

        [HttpGet]
        public IActionResult Register()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Register(RegisterViewModel model)
        {
            if (ModelState.IsValid)
            {
                var registrationSuccessful = await _registerService.RegisterUserAsync(model);
                if (registrationSuccessful)
                {
                    // Registration successful, redirect to login or another page
                    return RedirectToAction("Login");
                }
                else
                {
                    ModelState.AddModelError(string.Empty, "Username or Email already exists.");
                }
            }

            // If we got this far, something failed, redisplay the form
            return View(model);
        }

        [HttpGet]  // Aceasta este metoda HttpGet pentru Login
        public IActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(LoginViewModel model)
        {
            if (ModelState.IsValid)
            {
                var user = await _loginService.AuthenticateUserAsync(model);
                if (user != null)
                {
                    // Creează o listă de claim-uri pentru utilizator. Acestea vor fi incluse în cookie.
                    var claims = new List<Claim>
                    {
                        new Claim(ClaimTypes.Name, user.Username),
                        new Claim(ClaimTypes.Email, user.Email),
                        // Poți adăuga și alte claim-uri aici, dacă este necesar
                    };

                    // Creează un ClaimsIdentity și un ClaimsPrincipal
                    var claimsIdentity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);
                    var claimsPrincipal = new ClaimsPrincipal(claimsIdentity);

                    // Semnează utilizatorul
                    await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, claimsPrincipal);

                    return RedirectToAction("Index", "Home");
                }
                else
                {
                    ModelState.AddModelError(string.Empty, "Invalid login attempt.");
                }
            }

            // If we got this far, something failed, redisplay the form
            return View(model);
        }


        [HttpPost]  // Utilizează POST pentru a preveni delogarea accidentală prin accesarea directă a URL-ului
        [ValidateAntiForgeryToken]  // Protejează împotriva atacurilor CSRF
        public async Task<IActionResult> Logout()
        {
            // Șterge cookie-ul de autentificare
            await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme);
            
            // Redirecționează utilizatorul către pagina principală sau către pagina de login
            return RedirectToAction("Index", "Home");
        }

    }
}
