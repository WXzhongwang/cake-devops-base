/**
 * Base64Utils - 提供与文件内容Base64编码相关的工具函数。
 */


/**
 * 异步读取文件内容并将其转换为Base64编码的字符串。
 *
 * @param file - 要读取的文件对象（Blob 或 File 类型）
 * @returns Promise<string> - 承载Base64编码文件内容的Promise。
 */
export async function getBase64FromFile(file: Blob | File): Promise<string> {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();

        reader.onloadend = () => {
            if (reader.readyState === FileReader.DONE) {
                resolve(reader.result as string);
            } else {
                reject(new Error('Failed to read the file'));
            }
        };

        reader.onerror = (error) => {
            reject(error);
        };

        reader.readAsDataURL(file);
    });
}

