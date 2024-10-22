
// eslint-disable-next-line react/prop-types
const Product = ({ product }) => {
    // eslint-disable-next-line react/prop-types
    const { imgUrl, quantityBeds, quantityBaths, sqft, address } = product;

    return (
        <section className="mb-5 md:mb-0 bg-[#F6F6F6] w-full p-4 border rounded-[20px] shadow hover:shadow-2xl transition-shadow duration-300 cursor-pointer">
            <img src={imgUrl} alt={`View of ${address}`} className="w-full h-[198px]  object-cover rounded-3xl" />
            <div className="grid grid-cols-10 gap-4 text-lg mt-3 font-semibold">
                <div className="col-span-7 text-gray-700">
                    <span className="font-bold">{quantityBeds}</span> beds | <span className="font-bold">{quantityBaths}</span> baths | <span className="font-bold">{sqft}</span> sqft
                    <br />
                    <address className="not-italic">{address}</address>
                </div>
                <div className="col-span-3 text-right font-bold text-xl flex justify-center items-center">
                    DONE
                </div>
            </div>
        </section>
    );
};

export default Product;
