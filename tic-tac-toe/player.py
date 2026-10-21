from symbol import Symbol

class Player:
    def __init__(self, name=None):
        self._name = name
        self._symbol = None
        self._score = 0

    def set_name(self, name):
        self._name = name

    def get_name(self):
        return self._name

    def select_symbol(self, symbol):
        if not isinstance(symbol, Symbol):
            return
        self._symbol = symbol

    def get_symbol(self):
        return self._symbol

    def get_score(self):
        return self._score

    def increment_score(self):
        self._score += 1