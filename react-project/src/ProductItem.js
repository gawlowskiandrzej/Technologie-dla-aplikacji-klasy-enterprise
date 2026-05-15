
import {Link} from 'react-router-dom';

function ProductItem({ title, brand, id }) {
  return <li>
      <Link to={`/details/${id}`}>{title}</Link> ({brand})
    </li>;
}
export default ProductItem;