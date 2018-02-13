
from enum import Enum


class Drink(Enum):
    Tea = {'price': 0.4, 'code': 'T'}
    Chocolate = {'price': 0.5, 'code': 'H'}
    Covfefe = {'price': 0.6, 'code': 'C'}
    OrangeJuice = {'price': 0.6, 'code': 'O'}


class CoffeeMachine():

    @staticmethod
    def _genDrinkCommand(bevvie, sugarQty):
        outputStick = "0" if sugarQty > 0 else ""
        outputSugar = "%d" %sugarQty if sugarQty > 0 else ""
        return "%s:%s:%s" %(bevvie.value['code'],outputSugar,outputStick)


    @classmethod
    def order(cls, bevvie, sugarQty, money):
        price = bevvie.value['price']
        if money >= price:
            return cls._genDrinkCommand(bevvie, sugarQty)
        return "M:Missing %.2f" % (price - money)
