import { Building2, Hammer, Heart, Home, Ruler, Share } from 'lucide-react';
import PropertyInfomationSlide from "./Slide"
const PropertyInfomationBody = () => {
    return (
        <div className='grid grid-cols-5 gap-5 px-12 py-10'>
            <div className="col-span-4 mb-3">
                <div className="w-full flex justify-between items-center">
                    <div>
                        <div className="text-[32px] text-[#E6400C]">
                            585 Lakeview Circle
                        </div>
                        <div className="text-[25px]">
                            New Braunfels, TX 78130
                        </div>
                    </div>
                    <div className="text-6xl font-semibold text-black">
                        $ 1,499,000
                    </div>
                    <div className='flex gap-10'>
                        <div className='flex items-center gap-2 text-[25px]'>
                            <Heart size={"40px"} />
                            Save
                        </div>
                        <div className='flex items-center gap-2 text-[25px]'>
                            <Share size={"40px"} />
                            Share
                        </div>
                    </div>
                </div>
            </div>
            <div className="col-span-4">
                <PropertyInfomationSlide />
                <div className='grid grid-cols-2 mt-10'>
                    <div className='grid grid-cols-2 gap-3'>
                        <div className='bg-[#f6f6fa] flex items-center gap-3 p-2 font-medium text-xl'>
                            <Building2 />
                            Single Family Residence
                        </div>
                        <div className='bg-[#f6f6fa] flex items-center gap-3 p-2 font-medium text-xl'>
                            <Hammer />
                            Built in 1964
                        </div>
                        <div className='bg-[#f6f6fa] flex items-center gap-3 p-2 font-medium text-xl'>
                            <Ruler />
                            $238/sqft
                        </div>
                        <div className='bg-[#f6f6fa] flex items-center gap-3 p-2 font-medium text-xl'>
                            <Home />
                            $-- HOA
                        </div>
                    </div>
                    <div className='flex ms-20 gap-7 items-center '>
                        <div className='text-xl'>
                            <span className='text-5xl font-semibold'>3</span><br />beds
                        </div>
                        <div className='text-xl'>
                            <span className='text-5xl font-semibold'>2</span><br />baths
                        </div>
                        <div className='text-xl'>
                            <span className='text-5xl font-semibold'>1,876</span><br />sqft
                        </div>
                    </div>
                </div>
            </div>
            <div className="border p-5 border-red-700 h-fit rounded-[20px]">
                <button className="text-white font-semibold text-xl w-full bg-[#E6400C] focus:outline-none ring-0 hover:bg-red-600 active:bg-red-700">Request a tour<br /><span className="font-light text-sm">8:00 - 12:00 | 13:30 - 18:00</span></button>
                <button className="text-xl text-[#E6400C] font-semibold w-full flex justify-center items-center border mt-5 py-5 border-[#E6400C] hover:border-[#E6400C] ">Contact Agent</button>
            </div>
        </div>
    )
}

export default PropertyInfomationBody