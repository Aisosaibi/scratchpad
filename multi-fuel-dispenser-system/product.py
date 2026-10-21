from decimal import Decimal

def validate_amount(price):
    if price <= 0:
        raise ValueError("Price must be greater than 0")
    return price

class Product:
    def __init__(self, name :str, price: Decimal):
        self._name = name
        self._price = validate_amount(price)

    def get_price(self):
        return self._price

    def get_name(self):
        return self._name

    def set_price(self, new_price):
        self._price = validate_amount(new_price)

    def convert_amount_to_liter(self, amount):
        return amount / self._price

    def convert_liters_to_amount(self, liters):
        return liters * self._price



    