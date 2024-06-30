# Angular


## Modificadores de acesso

- **public**: O modificador padrão. Propriedades e métodos com este modificador são acessíveis de qualquer lugar.
- **private**: Propriedades e métodos com este modificador só são acessíveis dentro da própria classe. Eles não podem ser acessados de fora da classe, nem mesmo por subclasses.
- **protected**: Propriedades e métodos com este modificador são acessíveis dentro da classe e por qualquer classe derivada (subclasse). No entanto, não são acessíveis de fora dessas classes.
- **readonly**: Propriedades com este modificador só podem ser atribuídas durante a inicialização ou no construtor. Elas não podem ser modificadas depois.

Quando omitido, o modificador de acesso padrão aplicado é **public**.

## Injeção de dependência

Dependências podem ser injetadas no construtor da classe do componente ou, utilizando a função `inject()` do Angular Core. Seu uso é recomendado também quando for necessário injetar dependências dentro de outras funções.

## Formulários

As pricipais abordagens de formulários são: *template-driven* e *reactive*.

Quando utilizar o *template-driven*, atente-se para a aplicação do `NgModel`, do `FormsModule`. Também é importante notar a utilização do `NgValue`, quando tiver que lidar com property binding do atributo `value` utilizando objetos ao invés de valores literais.

Quando utilizar o *reactive*, atente-se para a aplicação do `FormControl`, `FormGroup` e `FormArray`. Ou, utilize o `FormBuilder` para substituir toda a cadeia de criação de objetos `Form*` por uma sintaxe mais compacta (previne vários `new` no código).
