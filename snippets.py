# Run with
# $ python3 snippets.py

import re

print('##################################################')
print('# Filter list of dict by dict key')
print('##################################################')
# Example: age is higher than 30
# Base data
people = [
    {'name': 'Alice', 'age': 28, 'country': 'USA'},
    {'name': 'Bob', 'age': 35, 'country': 'Canada'},
    {'name': 'Charlie', 'age': 42, 'country': 'France'},
    {'name': 'David', 'age': 29, 'country': 'Germany'},
]

# Using a list comprehension
older_than_30_A = [item for item in people if item['age'] > 30]

# Using list and filter functions with external function
def is_older_than_30(person):
    return person['age'] > 30

older_than_30_B = list(filter(is_older_than_30, people))

# Using list and filter functions inline lambda
older_than_30_C = list(filter(lambda item: item['age'] > 30, people))

# Print the filtered lists
print('\nUsing a list comprehension')
print(older_than_30_A)

print('\nUsing list and filter functions with external function')
print(older_than_30_B)

print('\nUsing list and filter functions inline lambda')
print(older_than_30_C)

print()
print('##################################################')
print('# Map list of dict by dict key')
print('##################################################')
# Example: map only name and country
# Using a list comprehension
mapped_dict_A = [{'new_name': item['name'], 'new_country': item['country']} for item in people]

# Using list and map functions with external function
def get_name_and_country(people):
    return {'new_name': people['name'], 'new_country': people['country']}

mapped_dict_B = list(map(get_name_and_country, people))

# Using list and map functions inline lambda
mapped_dict_C = list(map(lambda item: {'new_name': item['name'], 'new_country': item['country']}, people))

# Building list of strings using the name property
mapped_dict_D = [item['name'] for item in people]

print('Using a list comprehension')
print(mapped_dict_A)

print('\nUsing list and map functions with external function')
print(mapped_dict_B)

print('\nUsing list and map functions inline lambda')
print(mapped_dict_C)

print('\nBuilding list of strings using the name key')
print(mapped_dict_D)

print()
print('##################################################')
print('# Create list with unique values (remove duplicates)')
print('##################################################')
# Base data
string_list = ["apple", "banana", "apple", "cherry", "banana", "date"]

# Using to set transformation and to list transformation
unique_string_list = list(set(string_list))

print('\nUsing to set transformation and to list list transformation')
print(unique_string_list)

print()
print('##################################################')
print('# Unpack (spread) lists and objects')
print('##################################################')
print('\nUnpacking lists')
base_unpack_list = [1, 2, 3]
packed_list = [4, *base_unpack_list, 5]
print(packed_list) # Output [4, 1, 2, 3, 5]

print('\nUnpacking lists to use as function arguments')
def add_three_numbers(a, b, c):
    return a + b + c

result = add_three_numbers(*base_unpack_list)
print(result)  # Output 6

print('\nUnpacking dictionaries (python 3.5+)')
base_unpack_dictionary = {'a': 1, 'b': 2}
base_unpack_dictionary_A = {'a': 1, 'b': 2, 'c': 10}
packed_dict = {'c': 3, **base_unpack_dictionary} 
packed_dict_A = {'c': 3, **base_unpack_dictionary_A}
print(packed_dict) # Output {'c': 3, 'a': 1, 'b': 2}
print(packed_dict_A) # (overwrites previously existent properties) Output {'c': 10, 'a': 1, 'b': 2} ()

print()
print('##################################################')
print('# Check a string contains another string')
print('##################################################')
# Example: Check if a texc contains sample or THIS
main_string = 'This is a sample text.'
substring = 'sample'
another_substring = 'THIS'

print('Using in operator')
if substring in main_string:
    print(f"'{main_string}' contains '{substring}'.")
else:
    print(f"'{main_string}' does not contain '{substring}'.")

if another_substring in main_string:
    print(f"'{main_string}' contains '{another_substring}'.")
else:
    print(f"'{main_string}' does not contain '{another_substring}'.")


print('\nUsing .find() str function')
if main_string.find(substring) != -1:
    print(f"'{main_string}' contains '{substring}'.")
else:
    print(f"'{main_string}' does not contain '{substring}'.")

if main_string.find(another_substring) != -1:
    print(f"'{main_string}' contains '{another_substring}'.")
else:
    print(f"'{main_string}' does not contain '{another_substring}'.")


print('\nUsing re module .search() function')
if re.search(substring, main_string):
    print(f"'{main_string}' contains '{substring}'.")
else:
    print(f"'{main_string}' does not contain '{substring}'.")

if re.search(another_substring, main_string):
    print(f"'{main_string}' contains '{another_substring}'.")
else:
    print(f"'{main_string}' does not contain '{another_substring}'.")

print()
print('##################################################')
print('# String True (Truthy) and False (Falsy) check')
print('##################################################')
print('Check string not empty, empty and None')
not_empty_string = 'This string is not empty'
empty_string = ''
none_string = None
print(f'not_empty_string: {not_empty_string}')
print(f'empty_string: {empty_string}')
print(f'none_string: {none_string}')
print('\nRESULTS')
print('not_empty_string: Truthy') if (not_empty_string) else print('not_empty_string: Falsy')
print('empty_string: Truthy') if (empty_string) else print('empty_string: Falsy')
print('none_string: Truthy') if (none_string) else print('none_string: Falsy')
print('NOT not_empty_string: Truthy') if (not(not_empty_string)) else print('NOT not_empty_string: Falsy')
print('NOT empty_string: Truthy') if (not(empty_string)) else print('NOT empty_string: Falsy')
print('NOT none_string: Truthy') if (not(none_string)) else print('NOT none_string: Falsy')

print()
print('##################################################')
print('# Replace a string with another string')
print('##################################################')
original_string = 'Hello world, this is a sample string.'

# Replace all ocorrences of 'is' with 'NOT'
new_string = original_string.replace('is', 'NOT')

print("Replace all ocorrences of 'is' with 'NOT'")
print(new_string)








print()
print('##################################################')
print('# Auxiliary functions')
print('##################################################')
print('\nRemove duplicates from a dictionary')
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
    {'id': 10, 'name': 'Alice', 'country': 'China', 'age': 25, 'city': 'Beijing'},

]
#
# Returns a list of dictionary containing only unique values
# dict_list: list of dictionaries to be evaluated
# keys: (optional, default='all') list of keys to be evaluated: ['key1', 'key2']
#    case 'all': a dictionary will only be considered equal when all keys are
#        equal based on keys existing in the first dictionary keys
#
def distinct_dict_list(dict_list, keys='all'):
    if (not(len(dict_list))):
        raise Error('Unable to remove dictionary list duplicates: dictionary list must not be empty.')
    
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

print('all')
for item in distinct_dict_list(data_list):
    print(item)

print('name, age')
for item in distinct_dict_list(data_list, ['name', 'age']):
    print(item)

# Same as before, but using sets and string manipulation
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

print('all')
for item in distinct_dict_list_using_set(data_list):
    print(item)

print('name, age')
for item in distinct_dict_list_using_set(data_list, ['name', 'age']):
    print(item)








