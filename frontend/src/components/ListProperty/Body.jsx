import Product from "../Product";
import product1 from "../../assets/product1.png";
import product2 from "../../assets/product2.png";
import { Play } from "lucide-react";
const ListPropertyBody = () => {
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
        <div className="px-5 py-8 h-[500px] w-full grid grid-cols-5 gap-5 mb-10">
            <div className="col-span-3 ">
                <div className="text-black text-xl font-semibold">
                    Real Estate & Homes For Sale : 280,622 results
                </div>
                <div className="flex mt-3 w-5/6">
                    <span className="text-red-700 text-xl me-3">Sort:</span>
                    <div className="flex gap-x-5 gap-y-2 text-xl flex-row flex-wrap">
                        {["For you", "Price(High to low)", "Price(Low to high)", "Newest", "Bathroms", "Bedrooms", "Square Feet", "Lot Size"].map((item, index) => {
                            const active = 0;
                            return (
                                <div className={`${active === index && "text-red-700 w-fit font-semibold "} hover:cursor-pointer`} key={index}>{item}</div>
                            )
                        })}
                    </div>
                </div>
                <div className="grid grid-cols-2 gap-5 mt-5">
                    {
                        products.map((product, index) =>
                            <Product
                                key={index}
                                product={product}
                            />)
                    }
                </div>
            </div>
            <div className="col-span-2">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d7772918.635107642!2d-126.71840706867184!3d36.878064867494686!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x808fb9fe5f285e3d%3A0x8b5109a227086f55!2zQ2FsaWZvcm5pYSwgSG9hIEvhu7M!5e0!3m2!1svi!2s!4v1716905233953!5m2!1svi!2s" className='z-50 w-full h-full' width="589" height="713" allowfullscreen="" loading="lazy" referrerPolicy="no-referrer-when-downgrade"></iframe>
            </div>
            <div className="col-span-5 p-5 flex items-center justify-center w-full gap-4 mt-3">
                <div className="p-2 rounded-full border-red-600 hover:bg-red-300 cursor-pointer border w-10 h-10 flex items-center justify-center rotate-180">
                    <Play color="red" />
                </div>
                {Array.of(1, 2, 3, 4, "...").map((item, index) => {
                    return (
                        <div key={index + 1} className="p-2 rounded-full hover:bg-red-300 cursor-pointer border-red-600 border w-10 h-10 flex items-center justify-center text-xl">
                            {item}
                        </div>
                    )
                })}
                <div className="p-2 rounded-full border-red-600 hover:bg-red-300 cursor-pointer border w-10 h-10 flex items-center justify-center">
                    <Play color="red" />
                </div>
            </div>
        </div>
    )
}

export default ListPropertyBody