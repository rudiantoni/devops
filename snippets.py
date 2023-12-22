# Python official docs: https://www.python.org/
# Run with
#   $ python3 snippets.py

print('##################################################')
print('# Default non initialized variable values')
print('##################################################')
print('AHA! Python doesn\'t allow a the creation of a non initialized variables')
print('--------------------------------------------------')
print('Anyways, we can emulate that using the None value')
none_variable = None
print(none_variable) # Output: None

print()
print('##################################################')
print('# Lists - Different ways to create one')
print('##################################################')
print('There are not much ways to create a list')
print('--------------------------------------------------')
print('Using list literals')
print(['apple', 'banana', 'cherry']) # Output: ['apple', 'banana', 'cherry']

print('\nUsing NumPy arrays')
# pip install numpy
import numpy as np
print(np.array([1, 2, 3, 4, 5])) # Output: [1 2 3 4 5]

print('\nAssigning values to each list/nparray position')
list_assign = [1, 2, 3]
# First element in every array
list_assign[0] = 10
# Last element in a 3 elements array
list_assign[2] = 50
print(list_assign) # Output: [10, 2, 50]

print('\nObtaining list/nparray size')
print('--------------------------------------------------')
print('Obtaining size from list using len()')
print(len(['apple', 'banana', 'cherry'])) # Output: 3

print('\nObtaining size from nparray using len()')
print(len(np.array(['apple', 'banana', 'cherry']))) # Output: 3

print('\nObtaining size from nparray using .size')
print(np.array(['apple', 'banana', 'cherry']).size) # Output: 3

print('\nAcessing list/nparray first element')
print(['apple', 'banana', 'cherry'][0]) # Output: apple

print('\nAcessing list/nparray last element')
print('--------------------------------------------------')
print('Using len()')
list_last_element = [ 'apple', 'banana', 'cherry' ]
print(list_last_element[len(list_last_element) - 1]) # Output: cherry

print('\nUsing reverse indexing')
list_last_element = [ 'apple', 'banana', 'cherry' ]
print(list_last_element[-1]) # Output: cherry

# TODO: usar esta estrutura como exemplo
print('\nCreate an array from a range of indexes from another array')
print('--------------------------------------------------')
print('\nUsing start index and end index (exclusive), example')
list_sub = ['a', 'b', 'c', 'd', 'e']
print(list_sub[2:4]) # Output: ['c', 'd']

print('\nUsing start index and end index (exclusive), without the first element')
print(list_sub[1:len(list_sub)]) # Output: ['b', 'c', 'd', 'e']

print('\nUsing start index and end index (exclusive), without the last element')
print(list_sub[0:len(list_sub) - 1]) # Output: ['a', 'b', 'c', 'd']
# TODO: estrutura de exemplo até aqui

print()
print('##################################################')
print('# Filter list of dict by dict key')
print('##################################################')
print('\nFiltering where age is higher then 30')
print('--------------------------------------------------')
people_filter = [
  {'name': 'Alice', 'age': 28 },
  {'name': 'Bob', 'age': 35 },
  {'name': 'Charlie', 'age': 42 },
  {'name': 'David', 'age': 29 }
]

print('\nUsing a list comprehension')
older_than_30_A = [it for it in people_filter if it['age'] > 30]
print(older_than_30_A) # Output: [{'name': 'Bob', 'age': 35}, {'name': 'Charlie', 'age': 42}]

print('\nUsing list and filter functions with external function')
def is_older_than_30(person):
  return person['age'] > 30
older_than_30_B = list(filter(is_older_than_30, people_filter))
print(older_than_30_B) # Output: [{'name': 'Bob', 'age': 35}, {'name': 'Charlie', 'age': 42}]

print('\nUsing list and filter functions inline lambda')
older_than_30_C = list(filter(lambda it: it['age'] > 30, people_filter))
print(older_than_30_C) # Output: [{'name': 'Bob', 'age': 35}, {'name': 'Charlie', 'age': 42}]

print()
print('##################################################')
print('# Map list of dict by dict key')
print('##################################################')
print('Mapping objects with name and country keys')
print('--------------------------------------------------')
people_map = [
  {'name': 'Alice', 'age': 28, 'country': 'USA' },
  {'name': 'Bob', 'age': 35, 'country': 'Canada' },
  {'name': 'Charlie', 'age': 42, 'country': 'France' },
  {'name': 'David', 'age': 29, 'country': 'Germany' }
]
print('\nUsing a list comprehension')
mapped_dict_A = [{'new_name': it['name'], 'new_country': it['country']} for it in people_map]
print(mapped_dict_A) # Output: [{'new_name': 'Alice', 'new_country': 'USA'}, {'new_name': 'Bob', 'new_country': 'Canada'}, {'new_name': 'Charlie', 'new_country': 'France'}, {'new_name': 'David', 'new_country': 'Germany'}]

print('\nUsing list and map functions with external function')
def get_name_and_country(people):
  return {'new_name': people['name'], 'new_country': people['country']}
mapped_dict_B = list(map(get_name_and_country, people_map))
print(mapped_dict_B) # Output: [{'new_name': 'Alice', 'new_country': 'USA'}, {'new_name': 'Bob', 'new_country': 'Canada'}, {'new_name': 'Charlie', 'new_country': 'France'}, {'new_name': 'David', 'new_country': 'Germany'}]

print('\nUsing list and map functions inline lambda')
mapped_dict_C = list(map(lambda it: {'new_name': it['name'], 'new_country': it['country']}, people_map))
print(mapped_dict_C) # Output: [{'new_name': 'Alice', 'new_country': 'USA'}, {'new_name': 'Bob', 'new_country': 'Canada'}, {'new_name': 'Charlie', 'new_country': 'France'}, {'new_name': 'David', 'new_country': 'Germany'}]

print('\nBuilding list of by the key \'name\'')
print('--------------------------------------------------')
mapped_dict_D = [it['name'] for it in people_map]
print(mapped_dict_D) # Output: ['Alice', 'Bob', 'Charlie', 'David']

print()
print('##################################################')
print('# Next - Find first ocorrence in dict list by dict key or None otherwise')
print('##################################################')
print('Find first dict whose ages are higher than 10, 23, 28 and 45.')
print('--------------------------------------------------')
base_dict_next = [
  {'name': 'Alice', 'age': 20},
  {'name': 'Bob', 'age': 25},
  {'name': 'Charlie', 'age': 30},
  {'name': 'David', 'age': 35},
  {'name': 'Eve', 'age': 40},
]
higher_then_10 = next((it for it in base_dict_next if it['age'] > 10), None)
higher_then_23 = next((it for it in base_dict_next if it['age'] > 23), None)
higher_then_28 = next((it for it in base_dict_next if it['age'] > 28), None)
higher_then_45 = next((it for it in base_dict_next if it['age'] > 45), None)
print(higher_then_10) # Output: {'name': 'Alice', 'age': 20}
print(higher_then_23) # Output: {'name': 'Bob', 'age': 25}
print(higher_then_28) # Output: {'name': 'Charlie', 'age': 30}
print(higher_then_45) # Output: None

print()
print('##################################################')
print('# Create list with unique values (remove duplicates)')
print('##################################################')
print('Removing strings apple and banana.')
print('--------------------------------------------------')
print('\nUsing to set and to list list transformation')
string_list = ["apple", "banana", "apple", "cherry", "banana", "date"]
unique_string_list = list(set(string_list))
print(unique_string_list) # Output: ['apple', 'banana', 'date', 'cherry']

print()
print('##################################################')
print('# Unpack (spread) lists and dicts')
print('##################################################')
print('Adding elements to lists and dicts, overwriting previously existing keys in dicts')
print('--------------------------------------------------')
print('\nUnpacking lists')
base_unpack_list = [1, 2, 3]
packed_list = [4, *base_unpack_list, 5]
print(packed_list) # Output: [4, 1, 2, 3, 5]

print('\nUnpacking lists to use as function arguments')
def add_three_numbers(a, b, c):
  return a + b + c
result = add_three_numbers(*base_unpack_list)
print(result) # Output: 6

print('\nUnpacking dictionaries (python 3.5+)')
base_unpack_dictionary = {'a': 1, 'b': 2}
base_unpack_dictionary_A = {'a': 1, 'b': 2, 'c': 10}
packed_dict = {'c': 3, **base_unpack_dictionary} 
packed_dict_A = {'c': 3, **base_unpack_dictionary_A}
print(packed_dict) # Output: {'c': 3, 'a': 1, 'b': 2}
print(packed_dict_A) # Output: {'c': 10, 'a': 1, 'b': 2}

print()
print('##################################################')
print('# String operations')
print('##################################################')
print('Check string contains text')
print('--------------------------------------------------')
import re
main_string = 'This is a sample text.' # Main string
search_a = 'sample' # Exists on the main string
search_b = 'THIS' # Don't exists on the main string

print('\nUsing in operator')
print(search_a in main_string) # Output: True
print(search_b in main_string) # Output: False

print('\nUsing .find() string function')
print(main_string.find(search_a) != -1) # Output: True
print(main_string.find(search_b) != -1) # Output: False

print('\nUsing (regex) re module .search() function')
print(re.search(search_a, main_string)) # Output: <re.Match object; span=(10, 16), match='sample'>
print(re.search(search_b, main_string)) # Output: None

print('\nUsing (regex) re module .search() function in a if condition')
print(True) if (re.search(search_a, main_string)) else print(False) # Output: True
print(True) if (re.search(search_b, main_string)) else print(False) # Output: False

print('Split a string into an list by delimiter')
print('--------------------------------------------------')
split_string = 'apple,banana,cherry'
print(split_string.split(',')) # Output: ['apple', 'banana', 'cherry']

print()
print('##################################################')
print('# Truthy and falsy (true and false) values checking')
print('##################################################')
print('Strings: not empty string and empty string (normal and negated)')
print('--------------------------------------------------')
value = 'This string is not empty'
print(f'Value: ({value})')
print(True) if (value) else print(False) # Output: True
print(True) if (not(value)) else print(False) # Output: False

value = ''
print(f'Value: ({value})')
print(True) if (value) else print(False) # Output: False
print(True) if (not(value)) else print(False) # Output: True

print('\nNumber (integer): Check negative, 0 and positive (normal and negated)')
print('--------------------------------------------------')
value = -5
print(f'Value: ({value})')
print(True) if (value) else print(False)
print(True) if (not(value)) else print(False)

value = 0
print(f'Value: ({value})')
print(True) if (value) else print(False)
print(True) if (not(value)) else print(False)

value = 5
print(f'Value: ({value})')
print(True) if (value) else print(False)
print(True) if (not(value)) else print(False)

print('\nNumber (float): Check negative, 0 and positive (normal and negated)')
print('--------------------------------------------------')
value = -5.3
print(f'Value: ({value})')
print(True) if (value) else print(False)
print(True) if (not(value)) else print(False)

value = 0.0
print(f'Value: ({value})')
print(True) if (value) else print(False)
print(True) if (not(value)) else print(False)

value = 5.3
print(f'Value: ({value})')
print(True) if (value) else print(False)
print(True) if (not(value)) else print(False)

print('\nMisc: Check None (normal and negated)')
print('--------------------------------------------------')
value = None
print(f'Value: ({value})')
print(True) if (value) else print(False)
print(True) if (not(value)) else print(False)

print()
print('##################################################')
print('# Replace a string with another string')
print('##################################################')
print("Replace all ocorrences of 'is' with 'NOT'")
print('--------------------------------------------------')

original_string = 'Hello world, this is a sample string.'
new_string = original_string.replace('is', 'NOT')
print(new_string) # Output: Hello world, thNOT NOT a sample string.

print()
print('##################################################')
print('# Conditional structures')
print('##################################################')
print('Using normal if, if-else to run functions')
print('--------------------------------------------------')
if (True):
  print('Entered the if block')
# Output: Entered the if block

if (False):
  print('Entered the if block')
else:
  print('Entered the else block')
# Output: Entered the else block

print('\nUsing inline if (pseudo-ternary), if-else to run functions')
print('--------------------------------------------------')
print('Entered the if block') if (True) else None # Output: Entered the if block
print('Entered the if block') if (False) else print('Entered the else block') # Output: Entered the else block

print('\nUsing inline if (pseudo-ternary), if-else to return values')
print('--------------------------------------------------')
result = 10 if (True) else 20
print(result) # Output: 10

result = 10 if (False) else 20
print(result) # Output: 20

print()
print('##################################################')
print('# Repetitive structures')
print('##################################################')
print('Looping through a range of numbers from 1 to 10')
print('--------------------------------------------------')
for it in range(1, (10 + 1)):
  print(it)
# Output: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10

print('\nLooping through an dictionary keys')
print('--------------------------------------------------')
print('\nUsing for...in to access KEYS ONLY')
dict_key_loop = {'name': 'Alice', 'age': 25, 'occupation': 'Developer'}
for key in dict_key_loop:
  print(key, dict_key_loop[key])
### Output:
 # name Alice
 # age 25
 # occupation Developer
###

print('\nUsing for...in with .keys() dictionary function to access KEYS ONLY')
for key in dict_key_loop.keys():
  print(key, dict_key_loop[key])
### Output:
 # name Alice
 # age 25
 # occupation Developer
###

print('\nUsing for...in with .items() dictionary function to access KEYS AND VALUES')
for key, value in dict_key_loop.items():
  print(key, value)
### Output:
 # name Alice
 # age 25
 # occupation Developer
###

print('\nUsing for...in with enumerate() to access INDEXES, KEYS AND VALUES')
for index, (key, value) in enumerate(dict_key_loop.items()):
  print(index, key, value)
### Output:
 # 0 name Alice
 # 1 age 25
 # 2 occupation Developer
###

print()
print('##################################################')
print('# Custom auxiliary functions')
print('##################################################')
print('Remove duplicates from a dictionary list')
print('--------------------------------------------------')
data_list = [
  {'id': 1, 'name': 'Alice', 'country': 'USA', 'age': 25, 'city': 'New York'},
  {'id': 1, 'name': 'Alice', 'country': 'USA', 'age': 25, 'city': 'New York'},
  {'id': 2, 'name': 'Bob', 'country': 'Canada', 'age': 30, 'city': 'Toronto'},
  {'id': 2, 'name': 'Bob', 'country': 'Canada', 'age': 30, 'city': 'Toronto'},
  {'id': 3, 'name': 'Charlie', 'country': 'UK', 'age': 22, 'city': 'London'},
  {'id': 3, 'name': 'Charlie', 'country': 'UK', 'age': 22, 'city': 'London'},
  {'id': 4, 'name': 'David', 'country': 'Australia', 'age': 35, 'city': 'Sydney'},
  {'id': 4, 'name': 'David', 'country': 'Australia', 'age': 35, 'city': 'Sydney'},
  {'id': 5, 'name': 'Eve', 'country': 'France', 'age': 28, 'city': 'Paris'},
  {'id': 5, 'name': 'Eve', 'country': 'France', 'age': 28, 'city': 'Paris'},
  {'id': 6, 'name': 'Frank', 'country': 'Germany', 'age': 40, 'city': 'Berlin'},
  {'id': 6, 'name': 'Bob', 'country': 'Germany', 'age': 30, 'city': 'Berlin'},
  {'id': 7, 'name': 'Grace', 'country': 'Japan', 'age': 29, 'city': 'Tokyo'},
  {'id': 8, 'name': 'Helen', 'country': 'India', 'age': 27, 'city': 'Mumbai'},
  {'id': 9, 'name': 'Ivy', 'country': 'Brazil', 'age': 32, 'city': 'Sao Paulo'},
  {'id': 10, 'name': 'Jack', 'country': 'China', 'age': 31, 'city': 'Beijing'},
  {'id': 10, 'name': 'Alice', 'country': 'China', 'age': 25, 'city': 'Beijing'}
]

###
 # Returns a list of unique dictionaries based on the provided keys or all keys if none are specified, keeping only the first occurence of duplicates.
 # Args:
 #   - dict_list (list): The list of dictionaries to be evaluated.
 #   - keys (list, optional): A list of keys by which dictionaries should be compared to determine their uniqueness.
 #     If not specified, all dictionary keys present in the first item will be considered. Default is 'all'.
 # 
 # Returns:
 #   - list: A new list containing unique dictionaries based on the specified keys or all keys if none are provided.
###
###
 # PERFORMANCE TESTING: 100000 iterations, 1000 data set size.
 # Time generating data: 0.002482s
 # Time processing: 3506.001339s
###
def distinct_dict_list(dict_list, keys='all'):
  if (not(len(dict_list))):
    return dict_list
  
  unique_dict_list = []
  check_keys = []
  if (keys == 'all'):
    first_dict = dict_list[0] 
    for key in first_dict:
      check_keys.append(key)
  else:
    check_keys = keys

  for dict_item in dict_list:
    
    if(len(unique_dict_list)):
      found_unique_item = None
      for unique_item in unique_dict_list:
        equal_condition = True
        
        for key in check_keys:
          equal_condition = equal_condition and (unique_item[key] == dict_item[key])
          if (not(equal_condition)):
            break

        if (equal_condition):
          found_unique_item = unique_item
          break
      
      if (found_unique_item is None):
        unique_dict_list.append(dict_item)
    
    else:
      unique_dict_list.append(dict_item)
  
  return unique_dict_list

print('\nUsing manual key iteration method, considering all keys')
key_iteration_all = distinct_dict_list(data_list)
print(key_iteration_all)

print('\nUsing manual key iteration method, considering keys name and age')
key_iteration_name_age = distinct_dict_list(data_list, ['name', 'age'])
print(key_iteration_name_age)

###
 # Variation from distinct_dict_list()
 # Using sets and string manipulation
###
###
 # PERFORMANCE TESTING: 100000 iterations, 1000 data set size.
 # Time generating data: 0.002482s
 # Time processing: 122.708315s
###
def distinct_dict_list_using_set(dict_list, keys='all'):
  if (not(len(dict_list))):
    raise Error('Unable to remove dictionary list duplicates: dictionary list must not be empty.')

  unique_dict_set = set()
  unique_dict_list = []
  check_keys = []
  if (keys == 'all'):
    first_dict = dict_list[0] 
    for key in first_dict:
      check_keys.append(key)
  else:
    check_keys = keys

  for dict_item in dict_list:
    current_key_pair_str = ''

    for key in check_keys:
      current_key_pair_str += '__KP_START__' + f'{key}__KP_DIVISOR__{dict_item[key]}' + '__KP_START__'

    if (not(current_key_pair_str in unique_dict_set)):
      unique_dict_set.add(current_key_pair_str)
      unique_dict_list.append(dict_item)

  return unique_dict_list

print('\nUsing set building method, considering all keys')
set_building_all = distinct_dict_list_using_set(data_list)
print(set_building_all)

print('\nUsing set building method, considering keys name and age')
set_building_name_age = distinct_dict_list_using_set(data_list, ['name', 'age'])
print(set_building_name_age)

#***************************************************************************************************************************************************
#***************************************************************************************************************************************************
#***************************************************************************************************************************************************
#***************************************************************************************************************************************************
#***************************************************************************************************************************************************
# CODE TO PERFORMANCE TESTINGS
# import random
# from datetime import datetime
# def performance_test():
#   testing_amount = 100000
#   data_set_size = 1000
#   print(f'Using {testing_amount} iterations with a {data_set_size} data set size for performance test.')
#   dataList = []
#   possible_names = ['Alice','Bob','Charlie','David','Eve','Frank', 'Grace', 'Helen', 'Ivy', 'Jack']
#   possible_countries = ['USA', 'Canada', 'UK', 'Australia', 'France', 'Germany', 'Japan', 'India', 'Brazil', 'China']
#   possible_cities = ['New York', 'Toronto', 'London', 'Sydney', 'Paris', 'Berlin', 'Tokyo', 'Mumbai', 'Sao Paulo', 'Beijing']
#   min_age = 18
#   max_age = 50
#   current_id = 0;

#   start_gen_data = datetime.now()
#   print(f'Started - Generating data')
#   for i in range(0, data_set_size):
#     id = current_id
#     current_id += 1
#     name = possible_names[random.randint(0, len(possible_names) - 1)]
#     country = possible_countries[random.randint(0, len(possible_countries) - 1)]
#     age = random.randint(min_age, max_age)
#     city = possible_cities[random.randint(0, len(possible_cities) - 1)]
    
#     data_obj = {'id': id, 'name': name, 'country': country, 'age': age, 'city': city}
#     data_list.append(data_obj)

#   end_gen_data = datetime.now()
#   elapsed_seconds_gen_data = (end_gen_data - start_gen_data).total_seconds()
#   print(f'Finished - Generating data: Total {elapsed_seconds_gen_data}s')

#   start_test_stage_a = datetime.now()
#   print(f'Started STAGE A - Performance test')
#   for i in range(0, testing_amount):
#     testResult = distinct_dict_list(data_list)
#   end_test_stage_a = datetime.now()
#   elapsed_test_stage_a = (end_test_stage_a - start_test_stage_a).total_seconds()
#   print(f'Finished STAGE A - Performance test: Total {elapsed_test_stage_a}s')

#   start_test_stage_b = datetime.now()
#   print(f'Started STAGE B - Performance test')
#   for i in range(0, testing_amount):
#     testResult = distinct_dict_list_using_set(data_list)
#   end_test_stage_b = datetime.now()
#   elapsed_test_stage_b = (end_test_stage_b - start_test_stage_b).total_seconds()
#   print(f'Finished STAGE B - Performance test: Total {elapsed_test_stage_b}s')

# performance_test()
#***************************************************************************************************************************************************
#***************************************************************************************************************************************************
#***************************************************************************************************************************************************
#***************************************************************************************************************************************************
#***************************************************************************************************************************************************