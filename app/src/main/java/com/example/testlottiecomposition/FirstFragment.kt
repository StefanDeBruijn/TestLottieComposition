package com.example.testlottiecomposition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iv_1.withLottieAsync(R.raw.pull_to_refresh)
        iv_2.withLottieAsync(R.raw.control)

        iv_3.withLottieSync(R.raw.pull_to_refresh)
        iv_4.withLottieSync(R.raw.control)

        // iv_5 and iv_6 are set in XML with LottieAnimationView

        iv_7.withLottieAsset("pull_to_refresh.json")
        iv_8.withLottieAsset("control.json")
    }

    private fun ImageView.withLottieAsync(@RawRes lottieRes: Int) {
        val lottieDrawable = LottieDrawable()
        val lottieTask = LottieCompositionFactory.fromRawRes(context, lottieRes)
        lottieTask.addListener {
            lottieDrawable.composition = it
            lottieDrawable.repeatCount = LottieDrawable.INFINITE
        }

        setImageDrawable(lottieDrawable)
        lottieDrawable.start()
    }

    private fun ImageView.withLottieSync(@RawRes lottieRes: Int) {
        val lottieDrawable = LottieDrawable()
        val lottieTask = LottieCompositionFactory.fromRawResSync(context, lottieRes)
        lottieDrawable.composition = lottieTask.value
        lottieDrawable.repeatCount = LottieDrawable.INFINITE

        setImageDrawable(lottieDrawable)
        lottieDrawable.start()
    }

    private fun ImageView.withLottieAsset(lottieFile: String) {
        val lottieDrawable = LottieDrawable()
        val lottieTask = LottieCompositionFactory.fromAsset(context, lottieFile)
        lottieTask.addListener {
            lottieDrawable.composition = it
            lottieDrawable.repeatCount = LottieDrawable.INFINITE
        }

        setImageDrawable(lottieDrawable)
        lottieDrawable.start()
    }
}


