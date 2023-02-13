const express = require("express");
const jwt = require("jsonwebtoken");
const app = express();
const cors = require("cors");

app.use(cors());
app.use(express.json());

const users = [];

app.post("/register", (req, res) => {
  const { email, password } = req.body;
  const user = { email, password };
  users.push(user);
  res.status(201).send("User created");
});

app.post("/login", (req, res) => {
  const { email, password } = req.body;
  const user = users.find(u => u.email === email && u.password === password);
  if (!user) return res.status(400).send("Email or password is incorrect");
  const token = jwt.sign({ user }, "secretkey");
  res.send({ token });
});

recipes =  [
  {
  author: 'Ion Popescu',
  name: 'Supă de pui',
  ingredients: ['pui', 'morcovi', 'telina', 'ceapa', 'usturoi', 'bulion de pui', 'apa'],
  instructions: '1. Se curata si se taie legumele. 2. Se pune puiul intr-o oala mare si se adauga legumele. 3. Se toarna bulionul de pui si apa. 4. Se fierbe timp de 45 de minute. 5. Se poate servi cu o paine proaspata.',
  time: 60
  },
  {
  author: 'Maria Ion',
  name: 'Mititei',
  ingredients: ['carne de vita', 'ceapa', 'usturoi', 'patrunjel', 'piper', 'sare', 'cartofi', 'ulei'],
  instructions: '1. Se amesteca carnea de vita, ceapa, usturoi, patrunjel, piper si sare. 2. Se modeleaza mici cilindri. 3. Se fierb cartofii si se maruntesc. 4. Se amesteca cu carnea si se modeleaza din nou mici cilindri. 5. Se prajesc in ulei pana devin aurii.',
  time: 30
  },
  {
  author: 'Ioana Petrescu',
  name: 'Tocanita de vita',
  ingredients: ['vita', 'ceapa', 'usturoi', 'rosii', 'ardei', 'cartofi', 'bulion de vita', 'boia', 'sare', 'piper'],
  instructions: '1. Se taie carnea de vita in cuburi. 2. Se calesc ceapa, usturoiul, rosiile si ardeii in ulei. 3. Se adauga carnea si se prajeste. 4. Se adauga cartofii taiati in cuburi, bulionul de vita, boia, sare si piper. 5. Se fierbe timp de 45 de minute.',
  time: 60
  },
  {
  author: 'Dan Popescu',
  name: 'Musaca',
  ingredients: ['cartofi', 'carne de vita', 'ceapa', 'rosii', 'usturoi', 'smantana', 'sare', 'piper'],
  instructions: '1. Se fierb cartofii si se zdrobesc. 2. Se prajeste carnea de vita cu ceapa, usturoi si rosii. 3. Se amesteca smantana cu sare si piper. 4. Se aseaza o stratur de cartofi in fundul unui vas. 5. Se adauga o stratur de carne si se acopera cu smantana. 6. Se mai adauga inca 2-3 straturi pana la terminarea ingredientelor. 7. Se coace la cuptor timp de 30 de minute.',
  time: 45
},
{
author: 'Ionut Popescu',
name: 'Omleta cu branza si sunca',
ingredients: ['oua', 'branza', 'sunca', 'unt', 'sare', 'piper'],
instructions: '1. Intr-o tigaie, incalziti putin unt. 2. Bateti ouale cu sare si piper. 3. Adaugati sunca si branza taiate cubulete. 4. Turnati compozitia in tigaie. 5. Gatiti pe ambele parti pana ce devine aurie. 6. Serviti fierbinte.',
time: 20
},
{
author: 'Maria Ionescu',
name: 'Salata greceasca',
ingredients: ['rosii', 'castraveti', 'ceapa rosie', 'feta', 'ulei de masline', 'otet balsamic', 'busuioc'],
instructions: '1. Taiati rosiile si castravetii cubulete. 2. Adaugati ceapa rosie taiata marunt. 3. Adaugati bucati de branza feta. 4. Amestecati ulei de masline, otet balsamic si busuioc tocat. 5. Turnati dressing-ul peste salata. 6. Amestecati bine si serviti rece.',
time: 15
},
{
author: 'Andrei Vasile',
name: 'Cartofi prajiti',
ingredients: ['cartofi', 'ulei', 'sare'],
instructions: '1. Taiati cartofii cubulete sau felii subtiri. 2. Incingeti uleiul intr-o tigaie. 3. Adaugati cartofii si gatiti pana devin aurii. 4. Scoateti pe hartie absorbanta pentru a elimina excesul de ulei. 5. Condimentati cu sare. 6. Serviti fierbinte.',
time: 20
},
{
author: 'Elena Petrescu',
name: 'Tort de ciocolata',
ingredients: ['oua', 'zahar', 'faina', 'cacao', 'unt', 'lapte', 'esenta de vanilie'],
instructions: '1. Incalziti cuptorul la 180°C. 2. Bateti ouale cu zahar pana devin pufoase. 3. Adaugati faina si cacao amestecate. 4. Adaugati unt topit si lapte. 5. Adaugati esenta de vanilie. 6. Turnati compozitia in forma unsa cu unt. 7. Se coace timp de 25-30 de minute. 8. Lasati sa se raceasca si serviti cu frisca sau glazura.',
time: 60
},
];

app.post("/addRecipe", (req, res) => {
  const recipe = req.body;
  recipes.push(recipe);
  res.send({ message: "Recipe added successfully" });
});

app.get("/getRecipes", (req, res) => {
  res.send(recipes);
});

app.get("/searchRecipes", (req, res) => {
  const { searchTerm } = req.query;
  let filteredRecipes = [...recipes];

  if (searchTerm === '') {
    res.send(filteredRecipes);
    return;
  }

  filteredRecipes = filteredRecipes.filter((recipe) => {
    return recipe.author.toLowerCase().includes(searchTerm.toLowerCase()) ||
           recipe.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
           recipe.ingredients.toString().toLowerCase().includes(searchTerm.toLowerCase()) ||
           recipe.instructions.toLowerCase().includes(searchTerm.toLowerCase()) ||
           recipe.time == parseInt(searchTerm,10);
  });

  res.send(filteredRecipes);
});

app.get("/sortRecipes", (req, res) => {
  const { sortOption } = req.query;
  let sortedRecipes = [...recipes];

  switch (sortOption) {
    case 'author':
      sortedRecipes.sort((a, b) => (a.author > b.author) ? 1 : -1);
      break;
    case 'name':
      sortedRecipes.sort((a, b) => (a.name > b.name) ? 1 : -1);
      break;
    case 'ingredients':
      sortedRecipes.sort((a, b) => (a.ingredients.length > b.ingredients.length) ? 1 : -1);
      break;
    case 'instructions':
      sortedRecipes.sort((a, b) => (a.instructions.length > b.instructions.length) ? 1 : -1);
      break;
    case 'time':
        sortedRecipes.sort((a, b) => (a.time > b.time) ? 1 : -1);
        break;
    default:
        break;
  }
  res.send(sortedRecipes);
});


app.delete("/removeRecipe", (req, res) => {

  const {index, searchTerm, selectedOption} = req.body;
  let filteredRecipes = [...recipes];
  if (searchTerm) {
    filteredRecipes = filteredRecipes.filter(recipe => recipe.name.includes(searchTerm) || recipe.ingredients.join('').includes(searchTerm));
  }
  if (selectedOption === 'name') {
    filteredRecipes = filteredRecipes.sort((a, b) => a.name.localeCompare(b.name));
  } else if (selectedOption === 'time') {
    filteredRecipes = filteredRecipes.sort((a, b) => a.time - b.time);
  } else if (selectedOption === 'author') {
    filteredRecipes.sort((a, b) => (a.author > b.author) ? 1 : -1);
  } else if (selectedOption === 'ingredients') {
    filteredRecipes.sort((a, b) => (a.ingredients.length > b.ingredients.length) ? 1 : -1);
  } else if (selectedOption === 'instructions'){
    filteredRecipes.sort((a, b) => (a.instructions.length > b.instructions.length) ? 1 : -1);
  }

  filteredRecipes.splice(index, 1);
  recipes = [...filteredRecipes];
  res.send({ message: "Recipe removed successfully" });
});


app.listen(3000, () => console.log(`Server started at http://localhost:3000`));