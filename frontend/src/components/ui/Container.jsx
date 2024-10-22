
// eslint-disable-next-line react/prop-types
const Container = ({ children, className }) => {
    return (
        <div className={`${className} w-svw mx-[52px]`}>{children}</div>
    )
}

export default Container