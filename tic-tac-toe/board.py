from symbol import Symbol

class Board:
    def __init__(self):
        self._matrix = [[None, None, None] for _ in range(3)]

    def find_spots(self):
        available_spots = []
        for row_index, row in enumerate(self._matrix):
            for column_index, spot in enumerate(row):
                if spot is None:
                    available_spots.append([row_index, column_index])
        return available_spots

    def make_move(self, row, column, symbol):
        self._matrix[row][column] = symbol

    def validate_move(self, row, column, symbol):
        if not isinstance(symbol, Symbol):
            return False
        if self._matrix[row][column] is not None:
            return False
        else:
            return True


    def check_for_symbol_win(self, symbol):
        winning_lines = [
            [(0, 0), (0, 1), (0, 2)],
            [(1, 0), (1, 1), (1, 2)],
            [(2, 0), (2, 1), (2, 2)],
            [(0, 0), (1, 0), (2, 0)],
            [(0, 1), (1, 1), (2, 1)],
            [(0, 2), (1, 2), (2, 2)],
            [(0, 0), (1, 1), (2, 2)],
            [(0, 2), (1, 1), (2, 0)]
        ]

        for line in winning_lines:
            row0, col0 = line[0]
            row1, col1 = line[1]
            row2, col2 = line[2]
            if self._matrix[row0][col0] == self._matrix[row1][col1] == self._matrix[row2][col2] == symbol:
                return True
        return False

        # alternative logic:
        # for line in winning_lines:
        #     is_a_winner = True
        #     for row, column in line:
        #         if self._matrix[row][column] != symbol:
        #             is_a_winner = False
        #             break
        #     if is_a_winner:
        #         return True
        # return False

    def check_for_win(self):
        x_wins = self.check_for_symbol_win(Symbol.X)
        o_wins = self.check_for_symbol_win(Symbol.O)
        return x_wins or o_wins

    def check_for_draw(self):
        available_spots = self.find_spots()
        if len(available_spots) == 0:
            if not self.check_for_win():
                return True
        return False

    def reset_board(self):
        self._matrix = [[None, None, None] for _ in range(3)]

    # def check_for_win(self):
#     winning_lines = [
#         [(0, 0), (0, 1), (0, 2)],
#         [(1, 0), (1, 1), (1, 2)],
#         [(2, 0), (2, 1), (2, 2)],
#         [(0, 0), (1, 0), (2, 0)],
#         [(0, 1), (1, 1), (2, 1)],
#         [(0, 2), (1, 2), (2, 2)],
#         [(0, 0), (1, 1), (2, 2)],
#         [(0, 2), (1, 1), (2, 0)]
#     ]
#
#     for line in winning_lines:
#         row0, col0 = line[0]
#         row1, col1 = line[1]
#         row2, col2 = line[2]
#         if self._matrix[row0][col0] == self._matrix[row1][col1] == self._matrix[row2][col2] is not None:
#             return True
#     return False
