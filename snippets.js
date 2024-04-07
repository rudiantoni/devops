/*
Node official docs: https://nodejs.org/en
Run with
  $ node snippets
Or
  $ node snippets.js

If you don't have node installed, create a generic (index.html) page and add this script in the <head> tag:
  <script src="./snippets.js"></script>
*/
/**
 * Array
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array
 */
console.log('##################################################')
console.log('# Default non initialized variable values')
console.log('##################################################')
console.log('JS contains no types, but still allows to create non initialized variables')
console.log('--------------------------------------------------')

console.log('\nUsing let or var')
let noValueVariable
console.log(noValueVariable) // Output: undefined

console.log()
console.log('##################################################')
console.log('# Arrays - Different ways to create one')
console.log('##################################################')
console.log('There\'s some different ways to create arrays in JS, some.')
console.log('--------------------------------------------------')

console.log('\nUsing array literals')
console.log(['apple', 'banana', 'cherry']) // Output: [ 'apple', 'banana', 'cherry' ]

console.log('\nUsing array constructor')
console.log(new Array('apple', 'banana', 'cherry')) // Output: [ 'apple', 'banana', 'cherry' ]

console.log('\nUsing array.from() this way you can "copy" the array')
console.log(Array.from(['apple', 'banana', 'cherry'])) // Output: [ 'apple', 'banana', 'cherry' ]

console.log('\nUsing array.of()')
console.log(Array.of('apple', 'banana', 'cherry')) // Output: [ 'apple', 'banana', 'cherry' ]

console.log('\nUsing spread opeartor this way you can "copy" the array')
console.log([...['apple', 'banana', 'cherry']]) // Output: [ 'apple', 'banana', 'cherry' ]

console.log('\nUsing .fill()')
console.log(new Array(5).fill('apple')) // Output: [ 'apple', 'apple', 'apple', 'apple', 'apple' ]

console.log('\nUsing .concat()')
console.log(['apple'].concat(['banana', 'cherry'])) // Output: [ 'apple', 'banana', 'cherry' ]

console.log('\nUsing destructuring on an iterable')
const [destructStrToArrA, destructStrToArrB] = 'ab'
console.log([destructStrToArrA, destructStrToArrB]) // Output: [ 'a', 'b' ]

console.log('\nUsing spreading on an iterable')
console.log([...'ab']) // Output: [ 'a', 'b' ]

console.log('\nAssigning values to each array position')
const createArrayAccess = [ 'apple', 'banana', 'cherry' ]
// First element in every array
createArrayAccess[0] = 'orange'
// Last element in a 3 elements array
createArrayAccess[2] = 'pear'
console.log(createArrayAccess) // Output: [ 'orange', 'banana', 'pear' ]

console.log('\nObtaining array size')
console.log(['apple', 'banana', 'cherry'].length) // Output: 3

console.log('\nAcessing array first element')
console.log(['apple', 'banana', 'cherry'][0]) // Output: apple

console.log('\nAcessing array last element')
const createArrayAccessLast = [ 'apple', 'banana', 'cherry' ]
console.log(createArrayAccessLast[createArrayAccessLast.length - 1]) // Output: cherry

console.log()
console.log('##################################################')
console.log('# Filter list of objects by object property')
console.log('##################################################')
console.log('Filtering where age is higher then 30')
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
console.log('# Map list of objects by object property')
console.log('##################################################')
console.log('Mapping objects with name and country property')
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

console.log('\nBuilding list of by the property \'name\'')
console.log('--------------------------------------------------')
const mappedDataA = dataMap.map(it => it.name)
console.log(mappedDataA) // Output: [ 'Alice', 'Bob', 'Charlie', 'David' ]

console.log()
console.log('##################################################')
console.log('# Find - Find first ocorrence in object list by property or undefined otherwise')
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
console.log('Adding elements to lists and objects, overwriting previously existing properties in objects')
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
console.log('Check string contains text')
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
console.log('Strings: not empty string and empty string (normal and negated)')
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

value = NaN
console.log(`Value (${value})`)
value ? console.log(true) : console.log(false) // Output: false
!value ? console.log(true) : console.log(false) // Output: true

console.log()
console.log('##################################################')
console.log('# Replace a string with another string')
console.log('##################################################')
console.log('Replace all ocorrences of \'is\' with \'NOT\'')
console.log('--------------------------------------------------')
console.log('Using .replaceAll() string function')
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
console.log('\nUsing for...i array looping by index')
for (let i = 1; i <= 10; i++) {
  console.log(i);
}
// Output: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

console.log('\nUsing for...of iteration over array elements')
const arrLoopIteration = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
for (item of arrLoopIteration) {
  console.log(item);
}
// Output: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

console.log('\nLooping through an object keys')
console.log('--------------------------------------------------')
console.log('\nUsing for...in with .hasOwnProperty() object function to access KEYS ONLY')
const objPropLoop = {name: 'Alice', age: 25, occupation: 'Developer'}
for (let key in objPropLoop) {
  if (objPropLoop.hasOwnProperty(key)) { // ensure that the property is not inherited (even from the prototype chain)
    console.log(key, objPropLoop[key]);
  }
}
/* Output:
name Alice
age 25
occupation Developer
*/

console.log('\nUsing Object.keys() function to access KEYS ONLY')
Object.keys(objPropLoop).forEach((key) => {
  console.log(key, objPropLoop[key]);
});
/* Output:
name Alice
age 25
occupation Developer
*/

console.log('\nUsing Object.keys() function to access INDEXES AND KEYS')
Object.keys(objPropLoop).forEach((key, index) => {
  console.log(index, key, objPropLoop[key]);
});
/* Output:
0 name Alice
1 age 25
2 occupation Developer
*/

console.log('\nUsing Object.entries() function with reassignment to access KEYS AND VALUES')
Object.entries(objPropLoop).forEach((entry) => {
  const key = entry[0]
  const value = entry[1]
  console.log(key, value)
})
/* Output:
name Alice
age 25
occupation Developer
*/

console.log('\nUsing Object.entries() function with destructuring to access KEYS AND VALUES')
Object.entries(objPropLoop).forEach(([key, value]) => {
  console.log(key, value)
})
/* Output:
name Alice
age 25
occupation Developer
*/

console.log('\nUsing Object.values() function to access VALUES ONLY')
Object.values(objPropLoop).forEach((value) => {
  console.log(value);
});
/* Output:
Alice
25
Developer
*/

console.log('\nUsing Object.entries() function to access INDEXES, KEYS AND VALUES')
Object.entries(objPropLoop).forEach(([key, value], index) => {
  console.log(index, key, value);
});
/* Output:
0 name Alice
1 age 25
2 occupation Developer
*/

console.log()
console.log('##################################################')
console.log('# Objects - Different ways to create one')
console.log('##################################################')
console.log('There\'s not so much different ways to create objects in JS.')
console.log('--------------------------------------------------')

console.log('\nUsing Object.fromEntries() with a KEY VALUE PAIR ARRAY (array of 2 elements sized arrays)')
const arrEntries = [
  ['nome', 'João'],
  ['idade', 30],
  ['cidade', 'São Paulo']
];
const objFromArrEntries = Object.fromEntries(arrEntries)
console.log(objFromArrEntries) // Output: { nome: 'João', idade: 30, cidade: 'São Paulo' }

console.log()
console.log('##################################################')
console.log('# Custom auxiliary functions')
console.log('##################################################')
console.log('Remove duplicates from a object list')
console.log('--------------------------------------------------')
const dataDistinctObject = [
  {id: 1, name: 'Alice', country: 'USA', age: 25, city: 'New York'},
  {id: 1, name: 'Alice', country: 'USA', age: 25, city: 'New York'},
  {id: 2, name: 'Bob', country: 'Canada', age: 30, city: 'Toronto'},
  {id: 2, name: 'Bob', country: 'Canada', age: 30, city: 'Toronto'},
  {id: 3, name: 'Charlie', country: 'UK', age: 22, city: 'London'},
  {id: 3, name: 'Charlie', country: 'UK', age: 22, city: 'London'},
  {id: 4, name: 'David', country: 'Australia', age: 35, city: 'Sydney'},
  {id: 4, name: 'David', country: 'Australia', age: 35, city: 'Sydney'},
  {id: 5, name: 'Eve', country: 'France', age: 28, city: 'Paris'},
  {id: 5, name: 'Eve', country: 'France', age: 28, city: 'Paris'},
  {id: 6, name: 'Frank', country: 'Germany', age: 40, city: 'Berlin'},
  {id: 6, name: 'Bob', country: 'Germany', age: 30, city: 'Berlin'},
  {id: 7, name: 'Grace', country: 'Japan', age: 29, city: 'Tokyo'},
  {id: 8, name: 'Helen', country: 'India', age: 27, city: 'Mumbai'},
  {id: 9, name: 'Ivy', country: 'Brazil', age: 32, city: 'Sao Paulo'},
  {id: 10, name: 'Jack', country: 'China', age: 31, city: 'Beijing'},
  {id: 10, name: 'Alice', country: 'China', age: 25, city: 'Beijing'}
]

/**
 * Returns a list of unique dictionaries based on the provided keys or all keys if none are specified, keeping only the first occurence of duplicates.
 * Args:
 *  - objList (list): The list of objects to be evaluated.
 *  - properties (list, optional): A list of properties by which objects should be compared to determine their uniqueness.
 *    If not specified, all object properties present in the first item will be considered. Default is 'all'.
 * 
 * Returns:
 *   list: A new list containing unique objects based on the specified properties or all properties if none are provided.
 */
/*
/**
 * PERFORMANCE TESTING: 100000 iterations, 1000 data set size.
 * Time generating data: 0.002s
 * Time processing: 1199.382s
 */
const distinctObjList = (objList, properties = 'all') => {
  if (!objList.length) {
    return objList
  }
  const uniqueObjList = []
  let checkKeys = []

  if (properties === 'all') {
    const firstObj = objList[0]
    Object.keys(firstObj).forEach((key) => {
      checkKeys.push(key)
    });
  } else {
    checkKeys = properties
  }
  
  objList.forEach(objItem => {
    if (uniqueObjList.length) {
      let foundUniqueItem = null

      uniqueObjList.forEach(uniqueObjItem => {
        let equalCondition = true

        for (const checkKey of checkKeys) {
          equalCondition = equalCondition && objItem[checkKey] === uniqueObjItem[checkKey] 
          if (!equalCondition) {
            break
          }
        }

        if (equalCondition) {
          foundUniqueItem = uniqueObjItem
        }
      })  

      if (!foundUniqueItem) {
        uniqueObjList.push(objItem)
      }

    } else {
      uniqueObjList.push(objItem)
    }
  })
  return uniqueObjList
}

console.log('\nUsing manual key iteration method, considering all keys')
const manualKeyIterationAll = distinctObjList(dataDistinctObject)
console.log(manualKeyIterationAll)

console.log('\nUsing manual key iteration method, considering keys name and age')
const manualKeyIterationNameAge = distinctObjList(dataDistinctObject, ['name', 'age'])
console.log(manualKeyIterationNameAge)

/**
 * Variation from distinctObjList()
 * Using sets and string manipulation
 */
/**
 * PERFORMANCE TESTING: 100000 iterations, 1000 data set size.
 * Time generating data: 0.002s
 * Time processing: 80.872s
 */
const distinctObjList_usingSet = (objList, properties = 'all') => {
  if (!objList.length) {
    return objList
  }
  const uniqueObjSet = new Set()
  const uniqueObjList = []
  let checkKeys = []

  if (properties === 'all') {
    const firstObj = objList[0]
    Object.keys(firstObj).forEach((key) => {
      checkKeys.push(key)
    });
  } else {
    checkKeys = properties
  }
  
  objList.forEach(objItem => {
    let objItemStr = ''
      checkKeys.forEach(checkKey => {
        objItemStr += `__KP_START__${checkKey}__KP_DIVISOR__${objItem[checkKey]}__KP_END__`
      })

    if (!uniqueObjSet.has(objItemStr)) {
      uniqueObjList.push(objItem)
      uniqueObjSet.add(objItemStr)
    }
  })

  return uniqueObjList
}

console.log('\nUsing set building method, considering all keys')
const setBuildingAll = distinctObjList_usingSet(dataDistinctObject)
console.log(setBuildingAll)

console.log('\nUsing set building method, considering keys name and age')
const setBuildingNameAge = distinctObjList_usingSet(dataDistinctObject, ['name', 'age'])
console.log(setBuildingNameAge)

/***************************************************************************************************************************************************
***************************************************************************************************************************************************
***************************************************************************************************************************************************
***************************************************************************************************************************************************
****************************************************************************************************************************************************/
/*
// CODE TO PERFORMANCE TESTINGS
const performanceTest = () => {
  const testingAmount = 100000
  const dataSetSize = 1000
  console.log(`Using ${testingAmount} iterations with a ${dataSetSize} data set size for performance test.`)
  const dataList = []
  const possibleNames = ['Alice','Bob','Charlie','David','Eve','Frank', 'Grace', 'Helen', 'Ivy', 'Jack']
  const possibleCountries = ['USA', 'Canada', 'UK', 'Australia', 'France', 'Germany', 'Japan', 'India', 'Brazil', 'China']
  const possibleCities = ['New York', 'Toronto', 'London', 'Sydney', 'Paris', 'Berlin', 'Tokyo', 'Mumbai', 'Sao Paulo', 'Beijing']
  const minAge = 18
  const maxAge = 50
  let currentId = 0;

  const startGenData = new Date()
  console.log(`Started - Generating data`)
  for (let i = 0; i < dataSetSize; i++) {
    const id = currentId++
    const name = possibleNames[getRandomInt(0, possibleNames.length)]
    const country = possibleCountries[getRandomInt(0, possibleCountries.length)]
    const age = getRandomInt(minAge, maxAge + 1)
    const city = possibleCities[getRandomInt(0, possibleCities.length)]
    const dataObj = {id: id, name: name, country: country, age: age, city: city }
    dataList.push(dataObj)
  }
  const endGenData = new Date()
  const elapsedSecondsGenData = (endGenData.getTime() - startGenData.getTime()) / 1000
  console.log(`Finished - Generating data: Total ${elapsedSecondsGenData}s`)

  const startTestStageA = new Date()
  console.log(`Started STAGE A - Performance test`)
  for (let i = 0; i < testingAmount; i++) {
    const testResult = distinctObjList(dataList)
  }
  const endTestStageA = new Date()
  const elapsedTestStageA = (endTestStageA.getTime() - startTestStageA.getTime()) / 1000
  console.log(`Finished STAGE A - Performance test: Total ${elapsedTestStageA}s`)

  const startTestStageB = new Date()
  console.log(`Started STAGE B - Performance test`)
  for (let i = 0; i < testingAmount; i++) {
    const testResult = distinctObjList_usingSet(dataList)
  }
  const endTestStageB = new Date()
  const elapsedTestStageB = (endTestStageB.getTime() - startTestStageB.getTime()) / 1000
  console.log(`Finished STAGE B - Performance test: Total ${elapsedTestStageB}s`)
}

performanceTest()

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min) + min); // The maximum is exclusive and the minimum is inclusive
}
 */

/***************************************************************************************************************************************************
***************************************************************************************************************************************************
***************************************************************************************************************************************************
***************************************************************************************************************************************************
****************************************************************************************************************************************************/