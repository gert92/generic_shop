package controller;

import dto.Customer;
import repository.CustomerRepository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class CustomerController {

    CustomerRepository repository = new CustomerRepository();

    public Customer addCustomer(){
        String customerName = JOptionPane.showInputDialog("Enter the customers name: ");
        String customerBalance = JOptionPane.showInputDialog("Enter the amount in the customers balance. Example: 544.23");

        Customer customer = Customer.builder()
                .customerName(customerName)
                .balance(Float.parseFloat(customerBalance)).build();

        return repository.createCustomer(customer);

    }

    public List<Customer> findAllCustomers(){
        return repository.showAllCustomer();
    }

    public void displayAllCustomers(){
        List<Customer> customers = repository.showAllCustomer();
        customers.forEach(System.out::println);
    }

    public Customer findCustomerById(Long id){
        return repository.findCustomerById(id);
    }


    public Customer chooseCustomer(){
        StringBuilder stringBuilder = new StringBuilder("Select the id of the customer \n");
        List<Customer> customers = findAllCustomers();
        customers.forEach(m -> stringBuilder.append(m.getId()).append(" - ").append(m.getCustomerName()).append("\n"));
        return findCustomerById(Long.parseLong(JOptionPane.showInputDialog(stringBuilder)));
    }


    public void updateFields(Customer customer){
        repository.updateCustomer(customer);
        System.out.println("Customer Updated.");
    }

    public void removeCustomer(Customer customer){
        String response = repository.removeCustomer(customer);
        System.out.println(response);
    }


}
