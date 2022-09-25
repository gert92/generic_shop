package controller;

import dto.Customer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import repository.CustomerRepository;

import javax.swing.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerController {

    CustomerRepository customerRepository = new CustomerRepository();

    public void addCustomer(){
        String customerName = JOptionPane.showInputDialog("Enter the customers name: ");
        String customerBalance = JOptionPane.showInputDialog("Enter the amount in the customers balance. Example: 544.23");

        Customer customer = Customer.builder()
                .customerName(customerName)
                .balance(Float.parseFloat(customerBalance)).build();

        customerRepository.createCustomer(customer);

    }

    public List<Customer> findAllCustomers(){
        return customerRepository.showAllCustomer();
    }

    public void displayAllCustomers(){
        List<Customer> customers = customerRepository.showAllCustomer();
        customers.forEach(System.out::println);
    }

    public Customer findCustomerById(Long id){
        return customerRepository.findCustomerById(id);
    }


    public Customer chooseCustomer(){
        StringBuilder stringBuilder = new StringBuilder("Select the id of the customer \n");
        List<Customer> customers = findAllCustomers();
        customers.forEach(m -> stringBuilder.append(m.getId()).append(" - ").append(m.getCustomerName()).append("\n"));
        return findCustomerById(Long.parseLong(JOptionPane.showInputDialog(stringBuilder)));
    }


    public void updateFields(Customer customer){
        customerRepository.updateCustomer(customer);
        System.out.println("Customer Updated.");
    }

    public void removeCustomer(Customer customer){
        String response = customerRepository.removeCustomer(customer);
        System.out.println(response);
    }


}
