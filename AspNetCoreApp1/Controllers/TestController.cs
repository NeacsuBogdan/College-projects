using Microsoft.AspNetCore.Mvc;
namespace AspNetCoreApp1.Controllers;

[Route("test3")]
public class TestController : ControllerBase
{
    [HttpGet]
    public IActionResult Get()
    {
        return Content("Hello from Controller3!");
    }
}
