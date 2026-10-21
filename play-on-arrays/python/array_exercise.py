def sp_flip_array(array):
    left_pointer = 0
    right_pointer = 1
    for i in range(len(array/2)):
        if (array[left_pointer] + array[right_pointer]) % 2 == 0:
            left_pointer += 2
            right_pointer += 2
        else:
            temp = array[left_pointer]
            array[left_pointer] = array[right_pointer]
            array[right_pointer] = temp
            left_pointer += 2
            right_pointer += 2
    return array