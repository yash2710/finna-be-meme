package nirma.finna_be_meme;

import android.view.View;

/**
 * Created by yashtrivedi on 18/09/15.
 */
interface ClickListener {
    public void onClick(View view, int position);

    public void onLongClick(View view, int position);
}
