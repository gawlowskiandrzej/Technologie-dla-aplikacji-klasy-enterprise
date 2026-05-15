import './App.css';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import {
  createBrowserRouter,
  RouterProvider
} from 'react-router-dom';
import ProductList from './ProductList';
import ProductDetails from './ProductDetails';


function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('https://dummyjson.com/products')
      .then(response => {
        setProducts(response.data.products);
      });
  }, []);

  const router = createBrowserRouter([
    {
      path: '/',
      element: <ProductList products={products} />
    },
    {
      path: 'details/:id',
      element: <ProductDetails products={products} />
    }
  ]);

  return <RouterProvider router={router} />;
}

export default App;
