package controller;

import dto.Customer;
import repository.CustomerRepository;

import javax.swing.*;

public class CustomerController {

    CustomerRepository repository = new CustomerRepository();

    public void addCustomer(){
        String customerName = JOptionPane.showInputDialog("Enter the customers name: ");
        String customerBalance = JOptionPane.showInputDialog("Enter the amount in the customers balance. Example: 544.23");

        Customer customer = Customer.builder()
                .name(customerName)
                .balance(Float.valueOf(customerBalance)).build();

        repository.createCustomer(customer);

    }

}
