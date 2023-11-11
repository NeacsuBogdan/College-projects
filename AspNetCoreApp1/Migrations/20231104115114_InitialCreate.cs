using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace AspNetCoreApp1.Migrations
{
    /// <inheritdoc />
    public partial class InitialCreate : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Elements",
                columns: table => new
                {
                    ElementId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    ElementName = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: false),
                    Description = table.Column<string>(type: "nvarchar(max)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Elements", x => x.ElementId);
                });

            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    UserId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Username = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: false),
                    Password = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: false),
                    Email = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.UserId);
                });

            migrationBuilder.CreateTable(
                name: "Combinations",
                columns: table => new
                {
                    CombinationId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Element1Id = table.Column<int>(type: "int", nullable: false),
                    Element2Id = table.Column<int>(type: "int", nullable: false),
                    ResultElementId = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Combinations", x => x.CombinationId);
                    table.ForeignKey(
                        name: "FK_Combinations_Elements_Element1Id",
                        column: x => x.Element1Id,
                        principalTable: "Elements",
                        principalColumn: "ElementId");
                    table.ForeignKey(
                        name: "FK_Combinations_Elements_Element2Id",
                        column: x => x.Element2Id,
                        principalTable: "Elements",
                        principalColumn: "ElementId");
                    table.ForeignKey(
                        name: "FK_Combinations_Elements_ResultElementId",
                        column: x => x.ResultElementId,
                        principalTable: "Elements",
                        principalColumn: "ElementId");
                });

            migrationBuilder.CreateTable(
                name: "UserElements",
                columns: table => new
                {
                    UserElementId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    UserId = table.Column<int>(type: "int", nullable: false),
                    ElementId = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_UserElements", x => x.UserElementId);
                    table.ForeignKey(
                        name: "FK_UserElements_Elements_ElementId",
                        column: x => x.ElementId,
                        principalTable: "Elements",
                        principalColumn: "ElementId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_UserElements_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Worlds",
                columns: table => new
                {
                    WorldId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    UserId = table.Column<int>(type: "int", nullable: false),
                    WorldName = table.Column<string>(type: "nvarchar(255)", maxLength: 255, nullable: false),
                    ActiveElements = table.Column<string>(type: "nvarchar(max)", nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Worlds", x => x.WorldId);
                    table.ForeignKey(
                        name: "FK_Worlds_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Combinations_Element1Id",
                table: "Combinations",
                column: "Element1Id");

            migrationBuilder.CreateIndex(
                name: "IX_Combinations_Element2Id",
                table: "Combinations",
                column: "Element2Id");

            migrationBuilder.CreateIndex(
                name: "IX_Combinations_ResultElementId",
                table: "Combinations",
                column: "ResultElementId");

            migrationBuilder.CreateIndex(
                name: "IX_UserElements_ElementId",
                table: "UserElements",
                column: "ElementId");

            migrationBuilder.CreateIndex(
                name: "IX_UserElements_UserId",
                table: "UserElements",
                column: "UserId");

            migrationBuilder.CreateIndex(
                name: "IX_Worlds_UserId",
                table: "Worlds",
                column: "UserId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Combinations");

            migrationBuilder.DropTable(
                name: "UserElements");

            migrationBuilder.DropTable(
                name: "Worlds");

            migrationBuilder.DropTable(
                name: "Elements");

            migrationBuilder.DropTable(
                name: "Users");
        }
    }
}
