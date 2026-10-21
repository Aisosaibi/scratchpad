import re
from decimal import Decimal

class Stock:
    def __init__(self, symbol: str, name: str, previous_closing_price: Decimal, current_price: Decimal):
        self.__symbol = symbol
        self.__name = name
        self.__previous_closing_price = previous_closing_price
        self.__current_price = current_price

        self.set_symbol(self.__symbol)

    def get_symbol(self):
        return self.__symbol

    def set_symbol(self, symbol):
        pattern = r"[^a-zA-Z0-9]"
        if re.search(pattern, symbol):
            raise ValueError("Symbol contains invalid character(s)")
        self.__symbol = symbol.upper()

    def get_name(self):
        return self.__name

    def set_name(self):
        self.__name = self.__name.upper()

    def get_previous_closing_price(self):
        return self.__previous_closing_price

    def set_previous_closing_price(self, previous_closing_price):
        is_invalid = previous_closing_price is None or Decimal(previous_closing_price) < Decimal("0")
        if not is_invalid:
            self.__previous_closing_price = previous_closing_price
        else: raise ValueError("Price is invalid")

    def get_current_price(self):
        return self.__current_price

    def set_current_price(self, current_price):
        is_invalid = current_price is None or Decimal(current_price) < Decimal("0")
        if not is_invalid:
            self.__current_price = current_price
        else: raise ValueError("Amount is invalid")

    def get_change_percent(self):
        delta = self.get_current_price() - self.get_previous_closing_price()
        return delta/self.get_current_price()