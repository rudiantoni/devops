/*
Run with
  $ node snippets
Or
  $ node snippets.js

If you don't have node installed, create a generic (index.html) page and add this script in the <head> tag:
  <script src="./snippets.js"></script>
*/

console.log('##################################################')
console.log('# true (Truthy) and false (Falsy) values')
console.log('##################################################')
console.log('Strings: not empty string and empty string')
console.log('--------------------------------------------------')
let value = 'This string is not empty'
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

value = ''
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

console.log('\nNumber (integer): Check negative, 0, and positive')
console.log('--------------------------------------------------')
value = -5
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

value = 0
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

value = 5
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

console.log('\nNumber (float): Check negative, 0, and positive')
console.log('--------------------------------------------------')
value = -5.3
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

value = 0.0
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

value = 5.3
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

console.log('\nMisc: Check null and undefined')
console.log('--------------------------------------------------')
value = null
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)

value = undefined
value ? console.log(`${value}: Truthy`) : console.log(`${value}: Falsy`)
!value ? console.log(`(NOT) ${value}: Truthy`) : console.log(`(NOT) ${value}: Falsy`)