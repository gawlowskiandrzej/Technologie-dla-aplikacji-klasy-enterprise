import { NewCustomerForm } from "@/app/customers/_components/new-customer-form";
import { CustomersTable } from "@/app/customers/_components/customers-table";
export const dynamic = "force-dynamic"; 

export default function CustomersPage() { 

  return ( 
    <>
    <NewCustomerForm />
      <p>&nbsp;</p>
    <CustomersTable />
    </>
  ); 
}