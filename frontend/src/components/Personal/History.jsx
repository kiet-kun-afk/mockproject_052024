import Product from "../Product";
import product1 from "../../assets/product1.png";
import product2 from "../../assets/product2.png";
const PersonalHistory = () => {
    const products = [
        {
            imgUrl: product1,
            quantityBeds: 3,
            quantityBaths: 3,
            sqft: "2,001",
            address: "10304 Tungsten St,  Bakersfield, CA 93311"
        }, {
            imgUrl: product2,
            quantityBeds: 3,
            quantityBaths: 3,
            sqft: "2,001",
            address: "10304 Tungsten St,  Bakersfield, CA 93311"
        },
        {
            imgUrl: product1,
            quantityBeds: 3,
            quantityBaths: 3,
            sqft: "2,001",
            address: "10304 Tungsten St,  Bakersfield, CA 93311"
        }, {
            imgUrl: product2,
            quantityBeds: 3,
            quantityBaths: 3,
            sqft: "2,001",
            address: "10304 Tungsten St,  Bakersfield, CA 93311"
        }
    ]
    return (
        <div className="w-full">
            <h1 className="text-2xl mx-auto text-[#E6400C] md:text-[64px] w-fit p-8 font-semibold">TRANSACTION HISTORY</h1>
            <div className="block md:grid md:grid-cols-3 gap-x-20 gap-y-10 md:px-20 pb-20">
                {
                    products.map((product, index) =>
                        <Product
                            key={index}
                            product={product}
                        />)
                } </div>
        </div>
    )
}

export default PersonalHistory
