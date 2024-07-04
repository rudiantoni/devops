# Angular

- Useful links
  - [Home • Angular](https://angular.io/)
  - [Playground • Angular](https://angular.dev/playground)
  - [PrimeNG - Angular UI Component Library](https://primeng.org/)
    - [PrimeNG (17) - Angular UI Component Library](https://primeng.org/installation)
    - [PrimeNG (16 LTS) - Angular UI Component Library](https://www.primefaces.org/primeng-v16-lts/installation)
    - [PrimeNG (15 LTS) - Angular UI Component Library](https://www.primefaces.org/primeng-v15-lts/installation)
    - [Releases · primefaces/primeng](https://github.com/primefaces/primeng/releases)
- Content
  - [Angular CLI](#angular-cli)
  - [Starting a project](#starting-a-project)
    - [Creating](#creating)
    - [Common first steps](#common-first-steps)
    - [Default starting structure](#default-starting-structure)
    - [Starting structure option A](#starting-structure-option-a)
    - [Style bootstrap](#style-bootstrap)
    - [Common reset style](#common-reset-style)
  - [Information](#information)
    - [Access modifiers](#access-modifiers)
    - [Dependency injection](#dependency-injection)
    - [Forms](#forms)

## Angular CLI
[[Top]](#)<br />

- **Installing**
  - Requirements: Node and NPM
  - To install the most updated verison of Angular CLI globally
    - `$ npm install -g @angular/cli`
    - Use `ng v` or `ng help` to check if it was installed successfully
  - To install a specific version of your Angular CLI
    - `$ npm install -g @angular/cli@12`

- **Uninstalling**
  - To uninstall the globally installed Angular CLI
  - `npm uninstall -g @angular/cli`

## Starting a project
[[Top]](#)<br />

### Creating
[[Top]](#)<br />

- To create the project
- Run `$ ng new project-name`
  - A *project-name* directory will be created in the current directory.
  - The project version will match your current Angular CLI version
- Common settings:
  - Angular 17
    - Style format: Sass (SCSS)
    - Enable SSR: N
  - Angular 16
    - Style format: Sass (SCSS)
    - Use Anguar routing: Y
  - Angular 15
    - Style format: Sass (SCSS)
    - Use Anguar routing: Y
- To test if it's working
  - Run `$ cd project-name`
  - Run `$ ng serve` or `$ ng s`

### Common first steps
[[Top]](#)<br />

#### Default starting structure

Uses `AppComponent` as a routing point without common content between pages.

- **Common**
  - In the template `app.component.html`: copy everything below the comment *End of Placeholder.*
  - In the class `app.component.ts`: add the `template` property to the `@Component` decorator, and set its value to the content copied previously.
  - In the class `app.component.ts`: remove the `templateUrl` and `styleUrl` properties from the `@Component` decorator.
  - In the class `app.component.ts`: remove any existing code in the `AppComponent` class.
  - Delete the files: `app.component.html`, `app.component.scss`, and `app.component.spec.ts`.
  - Create your main component: `$ ng g c components/home`.
- **Angular 17+**
  - In the router `app.routes.ts`: add the main component to the general route. It should look like this:
```typescript
import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
export const routes: Routes = [
  {path: '', component: HomeComponent}
];
```
- **Angular 15 and 16**
  - In the router `app-routing.module.ts`: add the main component to the general route. It should look like this:
```typescript
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [{
  path: '', component: HomeComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
```

#### Starting structure option A
[[Top]](#)<br />

Uses `AppComponent` as a routing point with common content between pages.

- **Common**
  - In the template `app.component.html`: remove everything above the comment *End of Placeholder.*
  - In the template `app.component.html`: add the following content: `<p>app works!</p>`
  - In the style `app.component.scss`: remove everything.

### Style bootstrap
[[Top]](#)<br />

> You can change every SCSS mentioned here by a CSS, just keeps following the same pattern.

- Create a folder scss in *./src/*.
- Create a folder base in *./src/scss/*.
- Create a folder component in *./src/scss/*.
- Create a folder layout in *./src/scss/*.
- Create a folder theme in *./src/scss/*.
- Create a file _index.scss in *./src/scss/*.
- Create a file _index.scss in *./src/scss/base/*.
- Create a file _index.scss in *./src/scss/component/*.
- Create a file _index.scss in *./src/scss/layout/*.
- Create a file _index.scss in *./src/scss/theme/*.
- Create a file _variables.scss in *./src/scss/theme/*.
- Edit the file *./src/styles.scss*
```scss
@import "scss";
```
- Edit the file *./src/scss/_index.scss*
```scss
@import "base/index";
@import "component/index";
@import "layout/index";
@import "theme/index";
```
- Edit the file *./src/scss/theme/_index.scss*
```scss
@use "variables";
```
- Edit the file *./src/scss/theme/_variables.scss*
```scss
:root {
  --primary: #2fbf71;
  --primary-010: #21804d;

  --white: #fff;
  --black: #000;
}
```

### Common reset style
[[Top]](#)<br />

```scss
* {
  box-sizing: border-box;

}

html, body {
  height: 100%;
}

body {
  margin: 0;
  background: var(--white);
  font-family: Roboto, "Helvetica neue";
}

h1 {
  font-size: 22px;
}

h2, h3 {
  font-size: 14px;
}

h1, h2, h3, a {
  color: var(--black);
  text-decoration: none;
}

p {
  color: var(--black);
  font-size: 16px;
  margin: 10px 0;
}

ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
```

## Information
[[Top]](#)<br />

### Access modifiers
[[Top]](#)<br />

- **public**: The default modifier. Properties and methods with this modifier are accessible from anywhere.
- **private**: Properties and methods with this modifier are only accessible within the class itself. They cannot be accessed from outside the class, not even by subclasses.
- **protected**: Properties and methods with this modifier are accessible within the class and by any derived class (subclass). However, they are not accessible from outside these classes.
- **readonly**: Properties with this modifier can only be assigned during initialization or in the constructor. They cannot be modified afterward.

When omitted, the default access modifier applied is `public`.

### Dependency injection
[[Top]](#)<br />

Dependencies can be injected into the constructor of the component class or by using the inject() function from Angular Core. Its use is also recommended when dependencies need to be injected into other functions.

### Forms
[[Top]](#)<br />

The main approaches to forms are: *template-driven* and *reactive*.

When using *template-driven* forms, pay attention to the application of `NgModel` from the `FormsModule`. It is also important to note the use of `NgValue` when dealing with property binding of the `value` attribute using objects instead of literal values.

When using *reactive* forms, pay attention to the application of `FormControl`, `FormGroup`, and `FormArray`. Alternatively, use `FormBuilder` to replace the entire chain of `Form*` object creation with a more compact syntax (prevents multiple `new` statements in the code).
