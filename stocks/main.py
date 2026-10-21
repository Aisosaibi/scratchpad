import re
from decimal import Decimal

class Stock:
    def __init__(self, symbol: str, name: str, previous_closing_price: Decimal, current_price: Decimal):
        self.__symbol = symbol
        self.__name = name
        self.__previous_closing_price = previous_closing_price
        self.__current_price = current_price

    @property # decorator
    def symbol(self):
        return self.__symbol

    @symbol.setter
    def symbol(self, symbol: str):
        self.__symbol = symbol