from symbol import Symbol

class Game:
    def __init__(self, players, board, total_rounds=3):
        self._players = players
        self._board = board
        self._total_rounds = total_rounds
        self._current_round = 1

    # @classmethod (MY IDE put this out. What does it do?)
    # def assign_symbols(self, first_player_symbol= Symbol.X) -> None:

    def assign_symbols(self, first_player_symbol=Symbol.X):
        if not isinstance(first_player_symbol, Symbol):
            first_player_symbol = Symbol.X

        player1, player2 = self._players
        player1.select_symbol(first_player_symbol)

        if first_player_symbol == Symbol.X:
            second_player_symbol = Symbol.O
        else:
            second_player_symbol = Symbol.X

        player2.select_symbol(second_player_symbol)

    def get_current_player(self):
        pass

    def play_round(self):
        pass

    def play_game(self):
        pass