import React from 'react';
import { useParams, Link } from 'react-router-dom';

function ProductDetails({ products }) {
  const { id } = useParams();

  const filtered = products.filter(
    product => product.id === parseInt(id)
  );

  if (filtered.length === 0) {
    return null;
  }

  const product = filtered[0];

  return (
    <div>
      <h1>{product.title}</h1>
      <p>Category: {product.category}<br /></p>
      <p>Brand: {product.brand}<br /></p>
      <p>Description: {product.description}<br /></p>
      <p>Price: {product.price}<br /></p>
      <img src={product.thumbnail} alt={product.title} />
      <br />
      <Link to="/">Back to list</Link>
    </div>
  );
}

export default ProductDetails;