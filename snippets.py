# Run with
# $ python3 snippets.py

import re

print('##################################################')
print('# Filter list of dict by dict property')
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
older_than_30_A = [item for item in people if item["age"] > 30]

# Using list and filter functions with external function
def is_older_than_30(person):
    return person["age"] > 30

older_than_30_B = list(filter(is_older_than_30, people))

# Using list and filter functions inline lambda
older_than_30_C = list(filter(lambda item: item["age"] > 30, people))

# Print the filtered lists
print('\nUsing a list comprehension')
print(older_than_30_A)

print('\nUsing list and filter functions with external function')
print(older_than_30_B)

print('\nUsing list and filter functions inline lambda')
print(older_than_30_C)

print()
print('##################################################')
print('# Map list of dict by dict property')
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

print('\nBuilding list of strings using the name property')
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
print('# Replace a string with another string')
print('##################################################')
original_string = 'Hello world, this is a sample string.'

# Replace all ocorrences of 'is' with 'NOT'
new_string = original_string.replace('is', 'NOT')

print("Replace all ocorrences of 'is' with 'NOT'")
print(new_string)

