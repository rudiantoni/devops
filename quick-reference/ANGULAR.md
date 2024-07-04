# Angular

## References

- [Home • Angular](https://angular.io/)
- [Playground • Angular](https://angular.dev/playground)

## Angular CLI

- **Installing**
  - Requirements: Node and NPM
  - To install the most updated verison of Angular CLI globally
    - `$ npm install -g @angular/cli`
    - Use `ng -v` or `ng help` to check if it was installed successfully
  - To install a specific version of your Angular CLI
    - `$ npm install -g @angular/cli@12`

- **Uninstalling**
  - To uninstall the globally installed Angular CLI
  - `npm uninstall -g @angular/cli`

## Iniciando um projeto

- To create the project
- Run `$ ng new project-name`
  - A *project-name* directory will be created in the current directory.
  - The project version will match your current Angular CLI version
  - Common setup:
    - Style format: Sass (SCSS)
    - Enable SSR: N
- To test if it's working
  - Run `$ cd project-name`
  - Run `$ ng serve` or `$ ng s`

### Common first steps

- **Default starting structure**: Uses `AppComponent` as a routing point without common content between pages.
  - In the template `app.component.html`: copy everything below the comment *End of Placeholder.*
  - In the class `app.component.ts`: add the `template` property to the `@Component` decorator, and set its value to the content copied previously.
  - In the class `app.component.ts`: remove the `templateUrl` and `styleUrl` properties from the `@Component` decorator.
  - In the class `app.component.ts`: remove any existing code in the `AppComponent` class.
  - Delete the files: `app.component.html`, `app.component.scss`, and `app.component.spec.ts`.
  - Create your main component: `$ ng g c components/home`.
  - In the router `app.routes.ts`: add the main component to the general route. It should look like this:
```python
import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
export const routes: Routes = [
  {path: '', component: HomeComponent}
];
```

- **Starting structure option A**: Uses `AppComponent` as a routing point with common content between pages.
  - In the template `app.component.html`: remove everything above the comment *End of Placeholder.*
  - In the template `app.component.html`: add the following content: `<p>app works!</p>`
  - In the style `app.component.scss`: remove everything.

## Access modifiers
- **public**: The default modifier. Properties and methods with this modifier are accessible from anywhere.
- **private**: Properties and methods with this modifier are only accessible within the class itself. They cannot be accessed from outside the class, not even by subclasses.
- **protected**: Properties and methods with this modifier are accessible within the class and by any derived class (subclass). However, they are not accessible from outside these classes.
- **readonly**: Properties with this modifier can only be assigned during initialization or in the constructor. They cannot be modified afterward.

When omitted, the default access modifier applied is `public`.

## Dependency injection

Dependencies can be injected into the constructor of the component class or by using the inject() function from Angular Core. Its use is also recommended when dependencies need to be injected into other functions.

## Forms

The main approaches to forms are: *template-driven* and *reactive*.

When using *template-driven* forms, pay attention to the application of `NgModel` from the `FormsModule`. It is also important to note the use of `NgValue` when dealing with property binding of the `value` attribute using objects instead of literal values.

When using *reactive* forms, pay attention to the application of `FormControl`, `FormGroup`, and `FormArray`. Alternatively, use `FormBuilder` to replace the entire chain of `Form*` object creation with a more compact syntax (prevents multiple `new` statements in the code).
