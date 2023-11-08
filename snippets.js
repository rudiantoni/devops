/*
Node official docs: https://nodejs.org/en
Run with
  $ node snippets
Or
  $ node snippets.js

If you don't have node installed, create a generic (index.html) page and add this script in the <head> tag:
  <script src="./snippets.js"></script>
*/
console.log('##################################################')
console.log('# Filter list of objects by object key')
console.log('##################################################')
console.log('\nFiltering where age is higher then 30')
console.log('--------------------------------------------------')
const dataFilter = [
  {name: 'Alice', age: 28 },
  {name: 'Bob', age: 35 },
  {name: 'Charlie', age: 42 },
  {name: 'David', age: 29 }
]

console.log('\nUsing filter function')
const filteredData = dataFilter.filter(it => it.age > 30)
console.log(filteredData) // Output: [ { name: 'Bob', age: 35 }, { name: 'Charlie', age: 42 } ]

console.log()
console.log('##################################################')
console.log('# Map list of objects by object key')
console.log('##################################################')
console.log('\nMapping objects with name and country keys')
console.log('--------------------------------------------------')
const dataMap = [
  {name: 'Alice', age: 28, country: 'USA' },
  {name: 'Bob', age: 35, country: 'Canada' },
  {name: 'Charlie', age: 42, country: 'France' },
  {name: 'David', age: 29, country: 'Germany' }
]

console.log('\nUsing map function')
const mappedData = dataMap.map(it => ({newName: it.name, newCountry: it.country}))
console.log(mappedData)
/* Output:
[
  { newName: 'Alice', newCountry: 'USA' },
  { newName: 'Bob', newCountry: 'Canada' },
  { newName: 'Charlie', newCountry: 'France' },
  { newName: 'David', newCountry: 'Germany' }
]
*/

console.log('\nBuilding list of by the key \'name\'')
console.log('--------------------------------------------------')
const mappedDataA = dataMap.map(it => it.name)
console.log(mappedDataA) // Output: [ 'Alice', 'Bob', 'Charlie', 'David' ]

console.log()
console.log('##################################################')
console.log('# Find - Find first ocorrence in object list by key or undefined otherwise')
console.log('##################################################')
console.log('\nFind first dict whose ages are higher than 10, 23, 28 and 45.')
console.log('--------------------------------------------------')
const dataFind = [
  {name: 'Alice', age: 20},
  {name: 'Bob', age: 25},
  {name: 'Charlie', age: 30},
  {name: 'David', age: 35},
  {name: 'Eve', age: 40},
]
const higherThen10 = dataFind.find(it => it.age > 10)
const higherThen23 = dataFind.find(it => it.age > 23)
const higherThen28 = dataFind.find(it => it.age > 28)
const higherThen45 = dataFind.find(it => it.age > 45)
console.log(higherThen10) // Output: { name: 'Alice', age: 20 }
console.log(higherThen23) // Output: { name: 'Bob', age: 25 }
console.log(higherThen28) // Output: { name: 'Charlie', age: 30 }
console.log(higherThen45) // Output: undefined

console.log()
console.log('##################################################')
console.log('# Create list with unique values (remove duplicates)')
console.log('##################################################')
console.log('Removing strings apple and banana.')
console.log('--------------------------------------------------')
console.log('\nUsing to set and to list list transformation')
const repeatedStrList = ['apple', 'banana', 'apple', 'cherry', 'banana', 'date']
const uniqueRepeatedStrList = [... new Set(repeatedStrList)]
console.log(uniqueRepeatedStrList) // Output: [ 'apple', 'banana', 'cherry', 'date' ]

console.log()
console.log('##################################################')
console.log('# Spread lists and objects')
console.log('##################################################')
console.log('Adding elements to lists and objects, overwriting previously existing keys in dicts')
console.log('--------------------------------------------------')
console.log('\nSpreading lists')
const baseSpreadList = [1, 2, 3]
const spreadedList = [4, ...baseSpreadList, 5]
console.log(spreadedList) // Output: [ 4, 1, 2, 3, 5 ]

console.log('\nSpreading lists to use as function arguments')
const add_three_numbers = (a, b, c) => {
  return a + b + c
}
const spreadedListFunction = add_three_numbers(...baseSpreadList)
console.log(spreadedListFunction) // Output: 6

console.log('\nSpreading objects')
const baseSpreadObjectA = {a: 1, b: 2}
const baseSpreadObjectB = {a: 1, b: 2, c: 10}
const spreadedObjectA = {c: 3, ...baseSpreadObjectA}
const spreadedObjectB = {c: 3, ...baseSpreadObjectB}
console.log(spreadedObjectA) // Output: { c: 3, a: 1, b: 2 }
console.log(spreadedObjectB) // Output: { c: 10, a: 1, b: 2 }

console.log()
console.log('##################################################')
console.log('# String operations')
console.log('##################################################')
console.log('\nCheck string contains text')
console.log('--------------------------------------------------')
const mainString = 'This is a sample text.' // Main string
const searchA = 'sample' // Exists on the main string
const searchB = 'THIS' // Don't exists on the main string

console.log('\nUsing .includes() string function')
console.log(mainString.includes(searchA)) // Output: true
console.log(mainString.includes(searchB)) // Output: false

console.log('\nUsing .indexOf() string function')
console.log(mainString.indexOf(searchA) !== -1) // Output: true
console.log(mainString.indexOf(searchB) !== -1) // Output: false

console.log('\nUsing (regex) .test() regexp function')
console.log(/sample/.test(mainString)) // Output: true
console.log(/THIS/.test(mainString)) // Output: false

console.log()
console.log('##################################################')
console.log('# Truthy and falsy (true and false) values checking')
console.log('##################################################')
console.log('\nStrings: not empty string and empty string (normal and negated)')
console.log('--------------------------------------------------')
let value = 'This string is not empty'
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: true
!value ? console.log(true) : console.log(false) // Output: false

value = ''
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: false
!value ? console.log(true) : console.log(false) // Output: true

console.log('\nNumber (integer): Check negative, 0 and positive (normal and negated)')
console.log('--------------------------------------------------')
value = -5
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: true
!value ? console.log(true) : console.log(false) // Output: false

value = 0
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: false
!value ? console.log(true) : console.log(false) // Output: true

value = 5
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: true
!value ? console.log(true) : console.log(false) // Output: false

console.log('\nNumber (float): Check negative, 0 and positive (normal and negated)')
console.log('--------------------------------------------------')
value = -5.3
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: true
!value ? console.log(true) : console.log(false) // Output: false

value = 0.0
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: false
!value ? console.log(true) : console.log(false) // Output: true

value = 5.3
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: true
!value ? console.log(true) : console.log(false) // Output: false

console.log('\nMisc: Check null and undefined (normal and negated)')
console.log('--------------------------------------------------')
value = null
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: false
!value ? console.log(true) : console.log(false) // Output: true

value = undefined
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: false
!value ? console.log(true) : console.log(false) // Output: true

console.log()
console.log('##################################################')
console.log('# Replace a string with another string')
console.log('##################################################')
console.log('Replace all ocorrences of \'is\' with \'NOT\'')
console.log('--------------------------------------------------')
console.log('\nUsing .replaceAll() string function')
const baseReplaceString = 'Hello world, this is a sample string.'
const replaceString = 'NOT'
console.log(baseReplaceString.replaceAll('is', replaceString)) // Output: Hello world, thNOT NOT a sample string.

console.log('\nUsing (regex) .replace() string function to use instead of .replaceAll()')
console.log(baseReplaceString.replaceAll(/is/g, replaceString)) // Output: Hello world, thNOT NOT a sample string.

console.log()
console.log('##################################################')
console.log('# Conditional structures')
console.log('##################################################')
console.log('Using normal if, if-else to run functions')
console.log('--------------------------------------------------')
if (true) {
  console.log('Entered the if block')
}
// Output: Entered the if block

if (false){
  console.log('Entered the if block')
} else {
  console.log('Entered the else block')
}
// Output: Entered the else block

console.log('\nUsing single instruction if, if-else to run functions')
console.log('--------------------------------------------------')
if (true) console.log('Entered the if block')
// Output: Entered the if block

if (false)
  console.log('Entered the if block')
else
  console.log('Entered the else block')
// Output: Entered the else block

console.log('\nUsing ternary operator to run functions')
console.log('--------------------------------------------------')
true ? console.log('Entered the true condition block') : console.log('Entered the false condition block')
// Output: Entered the true condition block

false ? console.log('Entered the true condition block') : console.log('Entered the false condition block')
// Output: Entered the false condition block


console.log('\nUsing ternary operator to return values')
console.log('--------------------------------------------------')
let result = 0
result = true ? 10 : 20
console.log(result) // Output: 10

result = false ? 10 : 20
console.log(result) // Output: 20

console.log()
console.log('##################################################')
console.log('# Repetitive structures')
console.log('##################################################')
console.log('Looping through a range of numbers from 1 to 10')
console.log('--------------------------------------------------')
for (let i = 1; i <= 10; i++) {
  console.log(i);
}
// Output: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10


const arrayOfObjects = [
  { id: 1, name: 'Alice' },
  { id: 2, name: 'Bob' },
  { id: 1, name: 'Alice' },
  { id: 3, name: 'Charlie' },
];

const uniqueObjects = (arr, prop) => {
  const uniqueSet = new Set();
  return arr.filter(obj => !uniqueSet.has(obj[prop]) && uniqueSet.add(obj[prop]));
};

const uniqueById = uniqueObjects(arrayOfObjects, 'id');

console.log(uniqueById);
