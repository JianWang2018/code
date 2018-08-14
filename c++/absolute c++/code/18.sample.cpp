#include <iostream>
using std::cin;
using std::cout;

int main( )
{
    int donuts, milk;
    double dpg;
    cout << "Enter number of donuts:\n";
    cin >> donuts;
    cout << "Enter number of glasses of milk:\n";
    cin >> milk;

        dpg = donuts/milk;
        cout << donuts << " donuts.\n"
             << milk << " glasses of milk.\n"
             << "You have " << dpg
             << " donuts for each glass of milk.\n";

    cout << "End of program.\n";
    return 0;
} 
