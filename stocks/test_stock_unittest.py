import unittest
from decimal import Decimal

from stock import Stock


class StockTest(unittest.TestCase):
    def test_that_get_symbol_returns_stock_symbol(self):
        aapl_stock = Stock("AAPL", "Apple Inc", Decimal("201.5"), Decimal("326.59"))
        self.assertEqual(aapl_stock.get_symbol(), "AAPL")

    def test_that_set_symbol_handles_case_difference(self):
        aapl_stock = Stock("AaPl", "Apple Inc", Decimal("201.5"), Decimal("326.59"))
        self.assertEqual(aapl_stock.get_symbol(), "AAPL")

    def test_that_get_name_returns_name(self):
        gtco_stock = Stock("GTCO", "Guaranty Trust Holding Company PLC", Decimal("2.75"), Decimal("0.090") )
        self.assertEqual(gtco_stock.get_name(), "Guaranty Trust Holding Company PLC")

    def test_that_get_previous_closing_price(self):
        aapl_stock = Stock("AAPL", "Apple Inc", Decimal("201.5"), Decimal("326.59"))
        self.assertEqual(aapl_stock.get_previous_closing_price(), Decimal("201.5"))

    def test_that_get_current_price(self):
        gtco_stock = Stock("GTCO", "Guaranty Trust Holding Company PLC", Decimal("2.75"), Decimal("0.090"))
        self.assertEqual(gtco_stock.get_current_price(), Decimal("0.090"))

    def test_get_change_percent(self):
        aapl_stock = Stock("AAPL", "Apple Inc", Decimal("201.5"), Decimal("326.59"))
        aapl_stock_prev, aapl_stock_curr = aapl_stock.get_previous_closing_price(), aapl_stock.get_current_price()
        gtco_stock = Stock("GTCO", "Guaranty Trust Holding Company PLC", Decimal("2.75"), Decimal("0.090"))
        gtco_stock_prev, gtco_stock_curr = gtco_stock.get_previous_closing_price(), gtco_stock.get_current_price()
        self.assertEqual(aapl_stock.get_change_percent(), (aapl_stock_curr - aapl_stock_prev)/aapl_stock_curr)
        self.assertEqual(gtco_stock.get_change_percent(), (gtco_stock_curr - gtco_stock_prev)/gtco_stock_curr)

    def test_that_set_symbol_raises_value_error_for_invalid_symbol(self):
        with self.assertRaises(ValueError):
            Stock("*AaPl", "Apple Inc", Decimal("201.5"), Decimal("326.59"))

    def test_that_set_name_handles_case_difference(self):
        pass

    def test_that_set_previous_closing_price_changes_previous_price_if_value_greater_than_zero(self):
        sample_stock = Stock("AIBI", "AI Business Intelligence", Decimal("1000"), Decimal("10_000"))
        sample_stock.set_previous_closing_price(Decimal("5000"))
        self.assertEqual(Decimal("5000"), sample_stock.get_previous_closing_price())

    def test_that_set_previous_closing_price_raises_value_error_if_new_value_less_than_zero(self):
        sample_stock = Stock("AIBI", "AI Business Intelligence", Decimal("1000"), Decimal("10_000"))

        with self.assertRaises(ValueError):
            sample_stock.set_previous_closing_price("-1000")


if __name__ == '__main__':
    unittest.main()
