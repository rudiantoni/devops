# React

- Useful links
  - [React](https://react.dev/)
  - [React Playground](https://playcode.io/react)
  - [Vite | Next Generation Frontend Tooling](https://vitejs.dev/)
    - [Getting Started | Vite](https://vitejs.dev/guide/)
- Content
  - [Starting a project](#starting-a-project)
    - [Creating](#creating)

## Starting a project
[[Top]](#)<br />

### Creating with TypeScript and Vite
[[Top]](#)<br />

> The file main-reset.css intent is only to keep the code organized, you can either change the file name or add it into the main.css or any other global style file, as long as that reset style instructions are the very first applied styles

- To create the project
- Run `$ npm create vite@latest project-name -- --template react-ts`
  - A *project-name* directory will be created in the current directory.
- To test if it's working
  - Run `$ cd project-name`
  - Run `$ npm install`
  - Run `$ npm run dev`

### Common first steps
[[Top]](#)<br />

#### Default starting structure

- 1 - In the file `./index.html`, remove the favicon and change the title, and the file should look like this:
```html
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>ProjectName</title>
  </head>
  <body>
    <div id="root"></div>
    <script type="module" src="/src/main.tsx"></script>
  </body>
</html>
```
- 2 - Create a file called `main.css` and `main-reset.css` in `./src`. The file `main.css` can be empty, just keep in mind that it'll contain all your global styles. Apply this content to `main-reset.css`:
```css
/*
  http://meyerweb.com/eric/tools/css/reset/ 
  v2.0 | 20110126
  License: none (public domain)
*/
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed,
figure, figcaption, footer, header, hgroup,
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
  margin: 0;
  padding: 0;
  border: 0;
  font-size: 100%;
  font: inherit;
  vertical-align: baseline;
}
/* HTML5 display-role reset for older browsers */
article, aside, details, figcaption, figure,
footer, header, hgroup, menu, nav, section {
  display: block;
}
body {
  line-height: 1;
}
ol, ul {
  list-style: none;
}
blockquote, q {
  quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
  content: '';
  content: none;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
}
```
- 3 - Remove the files `./src/assets/react.svg`, `./src/index.css`, `./src/App.css` and `./src/App.tsx`
- 4 - Create the directory `./src/components`
- 5 - Create a file `./src/components/App.tsx`, it will be your main component file, often used for routing and so on. Apply this content:
```typescript
// Use React.FC when needed react elements such as children, defaultProps and propTypes
const App = (): JSX.Element => {
  return (
    <>
      <p>App</p>
    </>
  );
};

export default App;
```
- 6 - Fix the `./src/main.tsx` file, by adding only the existing imports and the `App` component:
```typescript
import React from "react";
import ReactDOM from "react-dom/client";
import App from "./components/App";
import "./main-reset.css";
import "./main.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
```
