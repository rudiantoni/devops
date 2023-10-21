# Run with
# $ python3 snippets.py

print('##################################################')
print('# Filter list of dict by dict key')
print('##################################################')
print('Filtering where age is higher then 30')
print('--------------------------------------------------')
people_filter = [
    {'name': 'Alice', 'age': 28 },
    {'name': 'Bob', 'age': 35 },
    {'name': 'Charlie', 'age': 42 },
    {'name': 'David', 'age': 29 }
]

print('\nUsing a list comprehension')
older_than_30_A = [it for it in people_filter if it['age'] > 30]
print(older_than_30_A)

print('\nUsing list and filter functions with external function')
def is_older_than_30(person):
    return person['age'] > 30
older_than_30_B = list(filter(is_older_than_30, people_filter))
print(older_than_30_B)

print('\nUsing list and filter functions inline lambda')
older_than_30_C = list(filter(lambda it: it['age'] > 30, people_filter))
print(older_than_30_C)

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
print(mapped_dict_A)

print('\nUsing list and map functions with external function')
def get_name_and_country(people):
    return {'new_name': people['name'], 'new_country': people['country']}
mapped_dict_B = list(map(get_name_and_country, people_map))
print(mapped_dict_B)

print('\nUsing list and map functions inline lambda')
mapped_dict_C = list(map(lambda it: {'new_name': it['name'], 'new_country': it['country']}, people_map))
print(mapped_dict_C)

print('\nBuilding list of by the key \'name\'')
print('--------------------------------------------------')
mapped_dict_D = [it['name'] for it in people_map]
print(mapped_dict_D)

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
print(higher_then_10)
print(higher_then_23)
print(higher_then_28)
print(higher_then_45)

print()
print('##################################################')
print('# Create list with unique values (remove duplicates)')
print('##################################################')
print('Removing strings apple and banana.')
print('--------------------------------------------------')
print('\nUsing to set and to list list transformation')
string_list = ["apple", "banana", "apple", "cherry", "banana", "date"]
unique_string_list = list(set(string_list))
print(unique_string_list)

print()
print('##################################################')
print('# Unpack (spread) lists and dicts')
print('##################################################')
print('Adding elements to lists and dicts, overwriting previously existing keys in dicts')
print('--------------------------------------------------')
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
print('Check if a text contains sample or THIS')
print('--------------------------------------------------')
import re
main_string = 'This is a sample text.'
substring = 'sample'
another_substring = 'THIS'

print('\nUsing in operator')
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
print('# True (Truthy) and False (Falsy) values')
print('##################################################')
print('Strings: not empty string and empty string')
print('--------------------------------------------------')
value = 'This string is not empty'
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')

value = ''
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')

print('\nNumber (integer): Check negative, 0 and positive')
print('--------------------------------------------------')
value = -5
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')

value = 0
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')

value = 5
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')

print('\nNumber (float): Check negative, 0 and positive')
print('--------------------------------------------------')
value = -5.3
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')

value = 0.0
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')

value = 5.3
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')

print('\nMisc: Check None')
print('--------------------------------------------------')
value = None
print(f'{value}: Truthy') if (value) else print(f'{value}: Falsy')
print(f'\tnot({value}): Truthy') if (not(value)) else print(f'\tnot({value}): Falsy')



print()
print('##################################################')
print('# Replace a string with another string')
print('##################################################')
print("Replace all ocorrences of 'is' with 'NOT'")
print('--------------------------------------------------')

original_string = 'Hello world, this is a sample string.'
new_string = original_string.replace('is', 'NOT')
print(new_string)

print()
print('##################################################')
print('# Conditional structures')
print('##################################################')
print('Using normal if, if-else to run functions')
print('--------------------------------------------------')
if (True):
    print('Entered the if block')

if (False):
    print('Entered the if block')
else:
    print('Entered the else block')

print('\nUsing inline if, if-else to run functions')
print('--------------------------------------------------')
print('Entered the if block') if (True) else None
print('Entered the if block') if (False) else print('Entered the else block')

print('\nUsing inline if, if-else to return values')
print('--------------------------------------------------')
result = 10 if (True) else 20
print(result)

result = 10 if (False) else 20
print(result)


print()
print('##################################################')
print('# Repetitive structures')
print('##################################################')
print('Looping through a range of numbers from 1 to 10')
print('--------------------------------------------------')
for it in range(1, (10 + 1)):
    print(it)


print()
print('##################################################')
print('# Custom auxiliary functions')
print('##################################################')
print('Remove duplicates from a dictionary')
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

print('\nUsing key iteration method, considering all keys')
key_iteration_all = distinct_dict_list(data_list)
print(key_iteration_all)

print('\nUsing key iteration method, considering keys name and age')
key_iteration_name_age = distinct_dict_list(data_list, ['name', 'age'])
print(key_iteration_name_age)

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

print('\nUsing set building method, considering all keys')
set_building_all = distinct_dict_list_using_set(data_list)
print(set_building_all)

print('\nUsing set building method, considering keys name and age')
set_building_name_age = distinct_dict_list_using_set(data_list, ['name', 'age'])
print(set_building_name_age)
