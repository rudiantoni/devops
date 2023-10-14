# Run with
# $ python3 snippets.py

##################################################
# Filter list of dict by dict property age is higher than 30
##################################################
# Base data
people = [
    {"name": "Alice", "age": 28},
    {"name": "Bob", "age": 35},
    {"name": "Charlie", "age": 42},
    {"name": "David", "age": 29},
]

# Using a list comprehension
older_than_30_A = [person for person in people if person["age"] > 30]

# Using list and filter functions with external function
def is_older_than_30(person):
    return person["age"] > 30

older_than_30_B = list(filter(is_older_than_30, people))

# Using list and filter functions inline lambda
older_than_30_C = list(filter(lambda person: person["age"] > 30, people))

# Print the filtered lists
print('Using a list comprehension')
print(older_than_30_A)

print('Using list and filter functions with external function')
print(older_than_30_B)

print('Using list and filter functions inline lambda')
print(older_than_30_C)
